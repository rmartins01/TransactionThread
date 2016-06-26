package com.gft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component // annotation is applied to instruct Spring to scan the service-layer beans
@Scope("prototype") // The prototype scope instructs Spring to instantiate a new instance of the bean every time a bean instance is requested
@Transactional(propagation=Propagation.MANDATORY) // No REQUIRED default propagation, MANDATORY supports a transaction if one already exists. 
												  // Throws an exception if there is no active transaction
public class RunnableThread implements Runnable {

	@Autowired // Inject the dependency
	private TestService service;

	// Commented because it is already injected 
//	public RunnableThread(TestService service) {
//		this.service = service;
//	}

	public void doInTransaction() {
		// Why does this method does not run inside a transaction if the method
		// has the @Transactional annotation?
		System.out.println("RunnableThread.doInTransaction");
		service.doInTransaction();
	
		//rollback test
		throw new RuntimeException("Rollback Test");
	}
	
	public void run() {
		doInTransaction();
	}
}
