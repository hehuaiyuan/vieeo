package com.vieeo.module.service;

import java.io.Serializable;

import com.vieeo.core.domain.Entity;
import com.vieeo.module.repository.GenericRepository;

/**
 * 
 * @author hehy
 *
 */
public interface GenericService<E extends Entity,T extends Serializable> extends GenericRepository<E,T>{

}
