package sample.principal;

import java.security.Principal;

import sample.RoleEnum;

public class StreamingPrincipal implements Principal, java.io.Serializable {

	private String subscription;
	
	public StreamingPrincipal(RoleEnum role) {
		this.subscription = role.toString();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return subscription;
	}
	
	@Override
	public String toString() {
		return "StreamingPrincipal: " + subscription;
	}

}
