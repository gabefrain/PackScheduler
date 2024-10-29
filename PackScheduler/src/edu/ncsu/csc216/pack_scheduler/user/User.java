package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The User class is an abstract class that contains all user information
 */
public abstract class User {

	/** User's first name */
	private String firstName;
	/** User's last name */
	private String lastName;
	/** User's id */
	private String id;
	/** User's email */
	private String email;
	/** User's password */
	private String password;

	/**
	 * Default constructor which constructs a User object by calling the setters for
	 * each of the fields. If any of the fields are invalid an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param firstName string value for first name
	 * @param lastName  string value for last name
	 * @param id        string value for id
	 * @param email     string value for email
	 * @param password  string value for password
	 * @throws IllegalArgumentException if any fields are invalid
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);

	}

	/**
	 * Returns User's first name
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets User's first name. If first name is null or any empty string an
	 * IllegalArgumentException is thrown
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException for null or empty first name
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns User's last name
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets User's last name. If last name is null or any empty string an
	 * IllegalArgumentException is thrown
	 * 
	 * @param lastName the last name of user to set
	 * @throws IllegalArgumentException for null or empty last name
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns User's id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets User's id. If id is null or any empty string an
	 * IllegalArgumentException is thrown
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException for null or empty id
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns User's email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets User's email If email is null, any empty string, doesn't contain an
	 * "@", doesn't contain an ".", or the index of the last "." character is
	 * earlier than the index of the first "@" character an IllegalArgumentException
	 * is thrown.
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException for null or empty email, or incorrect
	 *                                  formatting
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		int counterAt = 0;
		int counterPeriod = 0;
		int indexAt = 0;
		int indexPeriod = 0;

		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				counterAt++;
				if (counterAt == 1) {
					indexAt = i;
				}
			}
			if (email.charAt(i) == '.') {
				counterPeriod++;
				indexPeriod = i;
			}
		}

		if (counterAt == 0) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (counterPeriod == 0) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (indexPeriod < indexAt) {
			throw new IllegalArgumentException("Invalid email");
		}

		this.email = email;
	}

	/**
	 * Returns user password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets User's password. If password is null or any empty string an
	 * IllegalArgumentException is thrown
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException for null or empty password
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Generates a hashCode for User using all the fields.
	 * 
	 * @return hashCode for User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given User for equality to this User on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}