/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.horizon.container;

import org.horizon.container.entries.InternalCacheEntry;
import org.horizon.factories.scopes.Scope;
import org.horizon.factories.scopes.Scopes;

import java.util.Set;

/**
 * The main internal data structure which stores entries
 *
 * @author Manik Surtani (<a href="mailto:manik@jboss.org">manik@jboss.org</a>)
 * @since 4.0
 */
@Scope(Scopes.NAMED_CACHE)
public interface DataContainer extends Iterable<InternalCacheEntry> {

   /**
    * Retrieves a cached entry
    * @param k key under which entry is stored
    * @return entry, if it exists and has not expired, or null if not
    */
   InternalCacheEntry get(Object k);

   /**
    * Puts an entry in the cache along with a lifespan and a maxIdle time
    * @param k key under which to store entry
    * @param v value to store
    * @param lifespan lifespan in milliseconds.  -1 means immortal.
    * @param maxIdle max idle time for which to store entry.  -1 means forever.
    */
   void put(Object k, Object v, long lifespan, long maxIdle);

   /**
    * Tests whether an entry exists in the container
    * @param k key to test
    * @return true if entry exists and has not expired; false otherwise
    */
   boolean containsKey(Object k);

   /**
    * Removes an entry from the cache
    * @param k key to remove
    * @return entry removed, or null if it didn't exist or had expired
    */
   InternalCacheEntry remove(Object k);

   /**
    *
    * @return count of the number of entries in the container
    */
   int size();

   /**
    * Removes all entries in the container
    */
   void clear();

   /**
    * @return a set of keys contained in the container
    */
   Set<Object> keySet();

   /**
    * Purges entries that have passed their expiry time
    */
   void purgeExpired();
}
