package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ITEMS database table.
 * 
 */
@Entity
@Table(name="ITEMS", schema= "TESTUSERDB")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITEMS_ITEMID_GENERATOR", sequenceName="ITEMS_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEMS_ITEMID_GENERATOR")
	@Column(name="ITEM_ID")
	private String itemId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_COMPLETED")
	private Date dateCompleted;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_STARTED")
	private Date dateStarted;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;

	//bi-directional many-to-one association to Priority
	@ManyToOne
	@JoinColumn(name="PRIORITY_ID")
	private Priority priority;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private Status status;

	//bi-directional many-to-one association to Tasker
	@ManyToOne
	@JoinColumn(name="TASKER_ID")
	private Tasker tasker;

	public Item() {
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Date getDateCompleted() {
		return this.dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public Date getDateStarted() {
		return this.dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tasker getTasker() {
		return this.tasker;
	}

	public void setTasker(Tasker tasker) {
		this.tasker = tasker;
	}

}