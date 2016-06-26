package com.gft;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestService service = ctx.getBean(TestService.class);
		service.doInTransaction();
		
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx.getBean("taskExecutor");
		taskExecutor.execute(ctx.getBean(RunnableThread.class));

		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.shutdown();
		try {
			taskExecutor.getThreadPoolExecutor().awaitTermination(3, TimeUnit.SECONDS);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/* Old implementation
		 * Thread thread = new Thread(new RunnableThread(service));
		 * thread.start(); try { thread.join(); } catch (InterruptedException e)
		 * { e.printStackTrace(); }
		 */
	}
}
