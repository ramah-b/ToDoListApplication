package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRIORITIES database table.
 * 
 */
@Entity
@Table(name="PRIORITIES", schema= "TESTUSERDB")
@NamedQuery(name="Priority.findAll", query="SELECT p FROM Priority p")
public class Priority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRIORITIES_PRIORITYID_GENERATOR", sequenceName="PRIORITIES_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIORITIES_PRIORITYID_GENERATOR")
	@Column(name="PRIORITY_ID")
	private String priorityId;

	private String description;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="priority")
	private List<Item> items;

	public Priority() {
	}

	public String getPriorityId() {
		return this.priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
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
		item.setPriority(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setPriority(null);

		return item;
	}

}