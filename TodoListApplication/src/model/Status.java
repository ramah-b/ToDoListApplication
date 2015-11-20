package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUS_STATUSID_GENERATOR", sequenceName="STATUS_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUS_STATUSID_GENERATOR")
	@Column(name="STATUS_ID")
	private String statusId;

	private String description;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="status")
	private List<Item> items;

	public Status() {
	}

	public String getStatusId() {
		return this.statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setStatus(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setStatus(null);

		return item;
	}

}