package com.chenjxx.cache;

import java.io.Serializable;

/**
 * cache interface
 * @author LiuChen
 * @version 1.0.0.0 2018Äê2ÔÂ2ÈÕ
 */
public interface ICache<K extends Serializable, V> {
	/**
	 * Put<code>value<code>into the cache,
	 * if the cache has contained <code>key<code> already,
	 * override the old<code>value<code>
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);

	/**
	 * Put<code>value<code>into the cache,
	 * if the cache has contained <code>key<code> already,
	 * return the old<code>value<code>
	 * and not override the <code>value<code>
	 * @param key
	 * @param value
	 * @return 
	 * @return
	 */
	public V putIfAbsent(K key, V value);
	
	/**
	 * Get<code>value<code> by <code>key<code>
	 * if<code>key<code> is not exist, return<code>null<code>
	 * @param key
	 * @return
	 */
	public V get(K key);
	
	/**
	 * Return whether contains<code>key<code>
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key);
	
	/**
	 * Return whether contains<code>value<code>
	 * @param value
	 * @return
	 */
	public boolean containsValue(V value);
	
	/**
	 * Remove the<code>value<code> by <code>key<code>
	 * if <code>key<code> is not exist, return null
	 * else return the <code>value<code>
	 * @param key
	 * @return
	 */
	public V remove(K key);
	
	/**
	 * clear the cache
	 */
	public void clear();
	
	/**
	 * whether the <code>key<code> is validate
	 * @param key
	 * @return
	 */
	public boolean isValidate(K key);
	
	/**
	 * Invalidate the <code>key<code>
	 * @param key
	 */
	public void invalidate(K key);
	
	/**
	 * Invalidate all <code>keys<code>
	 * @param key
	 */
	public void invalidateAll(K key);
	
}
