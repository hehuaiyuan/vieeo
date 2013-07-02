package com.vieeo.core.build;


public interface Builder<R,P> {

	public R build(P context)throws Exception;

}
