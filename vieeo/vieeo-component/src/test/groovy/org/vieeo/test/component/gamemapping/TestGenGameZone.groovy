package org.vieeo.test.component.gamemapping;

import static org.junit.Assert.*

import java.nio.charset.Charset

import org.apache.commons.lang.StringUtils
import org.junit.Test
import org.vieeo.test.component.gamemapping.model.AreaMapping
import org.vieeo.test.component.gamemapping.model.ConsumeMapping
import org.vieeo.test.component.gamemapping.model.DeposititemMapping
import org.vieeo.test.component.gamemapping.model.GameListMapping
import org.vieeo.test.component.gamemapping.model.GameMapping
import org.vieeo.test.component.gamemapping.model.GamePaymentMapping
import org.vieeo.test.component.gamemapping.model.GroupMapping
import org.vieeo.test.component.gamemapping.model.ItemMapping
import org.vieeo.test.component.gamemapping.model.PaymethodMapping

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.component.xstream.XStreamComponent

class TestGenGameZone {

	private Map<String,String> zones = [:];

	private Map<String,String> servers = [:];

	private Map<String,String> games = [:];

	private Map<String,String> units = [:];

	private XStreamComponent gameComponent;

	private XStreamComponent gameZoneComponent;

	HttpClientComponent httpComponent = new HttpClientComponent();

	private static int cnt = 0;

	private String failedGameIds = "";

	static final String PATH ="D:/gameinfo/gamedetail2/";

	private List<String> excludeEsalesIds = ["10048","591","109","800109200","671","100001000"];

	private List<String> blankEsalesIds = ["0","116","120","121","146","188","205","756","777","100000300","100001000","100001700","100002300","100003000","100004300","580","101","106","20001","108","607","78","61","30","4","39","1","79","91","48","10048","50","100000600","200012500","200002300","89","671","88","591","17","5","14","13","11","591","200002300","200020500","200054100","200025000","200039400","200040700","200042000","201066700","201158000","583","710","202163100","203417200","800109200","800169500","800265900","800228300","800252300","800254400","800262100","800265900","800267900","800269700","800269900","800270700","800272200","10","95","601","590","906","695","629","6","22"]

	@Test
	public void test() {
		init();
		String gameListXml = httpComponent.doGet("http://esales.sdo.com/config/deposititems.xml", null,Charset.forName("gb2312"));
		GameListMapping gameList = gameComponent.parseXML(gameListXml);

		println "游戏总数:"+gameList.items.size();

		gameList.items = filterUnavailEsalesGame(gameList.items);

		List<ItemMapping> availGames = [];
		Map<String,String> unavailGames = [:];
		gameList.items.each {game ->
			String gameid = game.id;
			String sndaGameid = games.get(gameid);
			String zoneXml = httpComponent.doGet("http://esales.sdo.com/config/${gameid}.xml", null,Charset.forName("gb2312"));
			GameMapping gameZone = gameZoneComponent.parseXML(zoneXml);
			if(convertGame(gameZone)){
				game.id = sndaGameid;
				game.gameid = sndaGameid;
				availGames.add(game);
			}else {
				unavailGames.put(gameid, gameid);
			}
		}
		//保留有效游戏
		gameList.items = availGames;

		gameList.paymethods.each {payment ->
			String varea = "";
			payment.content?.split("[,，]").each {pgameid ->
				if(unavailGames.get(pgameid) == null){
					String sndaGameid = games.get(pgameid);
					if(StringUtils.isNotBlank(sndaGameid)){
						varea = varea+sndaGameid+",";  //替换标准区
					}
				}
			}
			payment.content = StringUtils.isBlank(varea)? "" : varea.substring(0, varea.length()-1);
		}

		String gameXml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>\n"+gameComponent.toXML(gameList);
		BufferedWriter writer = new File(PATH+"game.xml").newWriter('gb2312');
		gameXml.eachLine{line -> writer.writeLine(line); }
		writer.flush();
		writer.close();

		println "转换总数:"+cnt;
		println "失败易售游戏ID列表：${failedGameIds}";
	}

	private List<ItemMapping> filterUnavailEsalesGame(List<ItemMapping> items){
		List<ItemMapping> rs = [];
		print "过滤游戏：";
		items?.each {item ->
			if(!blankEsalesIds.contains(item.id)){
				print "${item.id},";
				return ;
			}

			if(excludeEsalesIds.contains(item.id)){
				print "${item.id},";
				return ;
			}
			rs.add(item);
		}
		print "\n";
		return rs;
	}

	private boolean convertGame(GameMapping game){
		String gameid = games.get(game.deposititem.gameid);
		if(StringUtils.isBlank(gameid)){
			failedGameIds = failedGameIds+game.deposititem.id+",";
			return false;
		}
		//区服转换
		List<ItemMapping> availZones = [];
		game.deposititem.areas?.each {zone ->
			String zkey = zone.id+"_"+gameid;
			String zvalue = zones.get(zkey);
			if(StringUtils.isNotBlank(zvalue)){
				List<ItemMapping> availServers = [];
				zone.groups.each {server ->
					String skey = server.id+"_"+zvalue+"_"+gameid;
					String svalue = servers.get(skey);  //替换标准服
					if(StringUtils.isNotBlank(svalue)){
						server.id = svalue;
						availServers.add(server);
					}
				}
				zone.groups = availServers;
				zone.id = zvalue;  //替换标准区
				availZones.add(zone);
			}
		}
		game.deposititem.areas = availZones;
		//payment转换
		game.deposititem.paymethods.each {payment ->
			String varea = "";
			payment.area?.split("[,，]").each {parea ->
				String zkey = "${parea}_${gameid}";
				String zvalue = zones.get(zkey);
				if(StringUtils.isNotBlank(zvalue)){
					varea = varea+zvalue+",";  //替换标准区
				}
			}
			payment.area = StringUtils.isBlank(varea)? "" : varea.substring(0, varea.length()-1);
		}
		//consume转换
		game.deposititem.consumes.each {consume ->
			String varea = null;
			String ukey = "${consume.id}_${gameid}";
			String unitId = units.get(ukey);
			if(StringUtils.isBlank(unitId)){
				return ;
			}
			consume.id = unitId;
			consume.area?.split("[,，]").each {carea ->
				String zkey = "${carea}_${gameid}";
				String zvalue = zones.get(zkey);
				if(StringUtils.isNotBlank(zvalue)){
					varea = varea+zvalue+",";  //替换标准区
				}
			}
			consume.area = StringUtils.isBlank(varea)? "" : varea.substring(0, varea.length()-1);
		}
		game.deposititem.gameid = gameid;  //替换标准游戏

		String newXml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>\n"+gameZoneComponent.toXML(game);
		BufferedWriter writer = new File(PATH+"${gameid}.xml").newWriter('gb2312');
		newXml.eachLine{line -> writer.writeLine(line); }
		writer.flush();
		writer.close();
		cnt++;
		return true;
	}

	private void init(){
		def classes = [
			GameListMapping.class,
			ItemMapping.class,
			GamePaymentMapping.class
		];

		def zoneClasses = [
			GameMapping.class,
			DeposititemMapping.class,
			PaymethodMapping.class,
			ConsumeMapping.class,
			AreaMapping.class,
			GroupMapping.class
		];


		gameComponent = new XStreamComponent(classes:classes,fieldCheck:false);

		gameZoneComponent = new XStreamComponent(classes:zoneClasses,fieldCheck:false);

		new File("D:\\game.csv").eachLine("gb2312"){line ->
			line = line.replace("\"", "");
			def data = line.split(",");
			games.put(data[1], data[0]);
		}

		new File("D:\\gameZone.csv").eachLine("gb2312"){line ->
			line = line.replace("\"", "");
			def data = line.split(",");
			zones.put(data[1]+"_"+data[3], data[0]);
		}

		new File("D:\\gameServer.csv").eachLine("gb2312"){line ->
			line = line.replace("\"", "");
			def data = line.split(",");
			servers.put(data[1]+"_"+data[3]+"_"+data[4], data[0]);
		}

		new File("D:\\chargeunit.csv").eachLine("gb2312"){line ->
			line = line.replace("\"", "");
			def data = line.split(",");
			units.put(data[1]+"_"+data[3], data[0]);
		}
	}
}



