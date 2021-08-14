package online.alphateam.api.server.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import online.alphateam.api.server.cache.CachePool;

@Component
public class BasicTimer {
	@Scheduled(fixedRate = 1000*60*15)
	public void cacheTimer() {
		CachePool.pool.forEach( (key,value) ->{			
			System.out.println(key);			
			if( System.currentTimeMillis() > value.getExpiryTime() ) {				
				CachePool.pool.remove(key);
			}			
		} );		
	}
}
