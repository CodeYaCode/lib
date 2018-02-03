package com.chenjxx.cache.mapCache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chenjxx.cache.CacheAbstract;

/**
 * Use concurrentHashMap as cache
 * @author LiuChen
 * @version 1.0.0.0 2018Äê2ÔÂ3ÈÕ
 */
public class ConcurrentMapCache<K extends Serializable, V> extends CacheAbstract<K, V> {
	// ------------------------- constants ------------------------- //
	/** Never expire */
	private final static int EXPIRE_MODE_DEFAULT = 0;
	/** Rely on last write time */
	private final static int EXPIRE_MODE_WRITE   = 1;
	/** Rely on last read time */
	private final static int EXPIRE_MODE_READ	 = 2;
	/** Rely on last write/read time */
	private final static int EXPIRE_MODE_ACCESS  = 3;
	// ------------------------- private ------------------------- //
	/** cache */
	private Map<K, V> cache = new ConcurrentHashMap<K, V>();
	/** record mills time */
	private Map<K, Long> millsTimeMap = new ConcurrentHashMap<K, Long>();
	/** expireTime */
	private Long expireTime = 0L;
	/** expire mode */
	private int expireMode = EXPIRE_MODE_DEFAULT;
	/** is all invalidate */
	private boolean isAllInvalidate = false;
	
	/* (non-Javadoc)
	 * @see com.chenjxx.cache.ICache#put(java.io.Serializable, java.lang.Object)
	 */
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		if (!this.isValidate(key)) {
			this.remove(key);
			return false;
		}
		return this.cache.containsKey(key);
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V remove(K key) {
		if (!this.cache.containsKey(key)) {
			return null;
		}
		V v = this.cache.remove(key);
		if (this.millsTimeMap.containsKey(key)) {
			this.millsTimeMap.remove(key);
		}
		return v;
	}

	@Override
	public void clear() {
		this.cache.clear();
		this.millsTimeMap.clear();
		this.isAllInvalidate = false;
	}

	@Override
	public boolean isValidate(K key) {
		if (!this.cache.containsKey(key)) {
			return false;
		}
		if (!this.millsTimeMap.containsKey(key)) {
			return false;
		}
		if (this.isAllInvalidate) {
			return false;
		}
		Long lastTime = this.millsTimeMap.get(key);
		return this.expireMode == EXPIRE_MODE_DEFAULT | System.currentTimeMillis() >= (lastTime + this.expireTime);
	}

	@Override
	public void invalidate(K key) {
		if (!this.cache.containsKey(key)) {
			return ;
		}
		if (!this.millsTimeMap.containsKey(key)) {
			return ;
		}
		this.millsTimeMap.put(key, 0L);
	}

	@Override
	public void invalidateAll(K key) {
		this.isAllInvalidate = true;
	}
	
	/**
	 * Handle key's expire time
	 * @param key
	 */
	private void handleExpireTime(K key) {
		switch(this.expireMode) {
			case EXPIRE_MODE_READ:
				break;
			case EXPIRE_MODE_WRITE:
				break;
			case EXPIRE_MODE_ACCESS:
				break;
			case EXPIRE_MODE_DEFAULT:
				// DO nothing
			default:
				break;
		}
	}
	
}
