package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TASKERS database table.
 * 
 */
@Entity
@Table(name="TASKERS", schema= "TESTUSERDB")
@NamedQuery(name="Tasker.findAll", query="SELECT t FROM Tasker t")
public class Tasker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TASKERS_TASKERID_GENERATOR", sequenceName="TASKERS_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TASKERS_TASKERID_GENERATOR")
	@Column(name="TASKER_ID")
	private String taskerId;

	private String email;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="tasker")
	private List<Item> items;

	public Tasker() {
	}

	public String getTaskerId() {
		return this.taskerId;
	}

	public void setTaskerId(String taskerId) {
		this.taskerId = taskerId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setTasker(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setTasker(null);

		return item;
	}

}