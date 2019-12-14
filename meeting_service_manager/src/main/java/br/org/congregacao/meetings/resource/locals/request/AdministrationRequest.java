package br.org.congregacao.meetings.resource.locals.request;

import java.io.Serializable;
import java.util.List;

public final class AdministrationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String cnpj;
	private List<Church> churches;
	
	public AdministrationRequest() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Church> getChurches() {
		return churches;
	}

	public void setChurches(List<Church> churches) {
		this.churches = churches;
	}
	
}

final class Church implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String code;
	private List<Room> rooms;
	
	public Church() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
}

final class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public Room() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}