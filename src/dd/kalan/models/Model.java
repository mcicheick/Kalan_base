/**
 * 
 */
package dd.kalan.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author sissoko
 * @date 16 févr. 2015 11:56:16
 */
@MappedSuperclass
public abstract class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id")
	protected Long id;
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	protected Date creationDate;
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFICATION_DATE")
	protected Date modificationDate;
	@Basic(optional = false)
	@Column(name = "ACTIVE")
	protected boolean active;

	/**
	 * 
	 */
	public Model() {
		this.active = true;
		this.creationDate = new Date();
		this.modificationDate = new Date();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

}
