package com.vo;

public class FilmsXActorVO {
	Integer actorId;
	String firstName;
	String lastName;
	Long totalMovies;

	public FilmsXActorVO(Integer actorId, String firstName, String lastName, Long totalMovies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalMovies = totalMovies;
		this.actorId = actorId;
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getTotalMovies() {
		return totalMovies;
	}

	public void setTotalMovies(Long totalMovies) {
		this.totalMovies = totalMovies;
	}

	@Override
	public String toString() {
		return "FilmsXActorVO [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", totalMovies=" + totalMovies + "]";
	}

}
