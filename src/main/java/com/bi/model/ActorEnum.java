package com.bi.model;

public enum ActorEnum {

	DOCTOR("DOCTOR", 10),
	CLINIC("CLINIC", 7),
	SYSTEM("SYSTEM", 2);

	private ActorEnum(String actorType, int order) {
		this.actorType = actorType;
		this.order = order;
	}

	String actorType;
	int order;

	public String getActorType() {
		return this.actorType;
	}

	public int getOrder() {
		return this.order;
	}
}
