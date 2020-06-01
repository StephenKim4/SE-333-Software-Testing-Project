package data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
	private final String _title;
	private final int    _year;
	private final String _director;

	/**
	 * Initialize all object attributes.
	 * 
	 * @param title       title
	 * @param year        year
	 * @param director    director
	 */
	public VideoObj(String title, int year, String director) {
		if (title == (null) || title.equals("") || title.equals(" "))
			throw new IllegalArgumentException("Title must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
		if (director == null || director.equals("") || director.equals(" "))
			throw new IllegalArgumentException("Director must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
		if (year <= 1800 || year >= 5000)
			throw new IllegalArgumentException("Year must be greater than 1800 or less than 5000");
		_title = title;
		_director = director;
		_year = year;
	}

	/**
	 * Return director
	 */
	public String director() {
		return _director;
	}

	/**
	 * Return title
	 */
	public String title() {
		return _title;
	}

	/**
	 * Return year
	 */
	public int year() {
		return _year;
	}

	/**
	 * Return true if objects are equal, otherwise return false
	 */
	public boolean equals(Object thatObject) {

		//If object is null return false
		if (thatObject == null)
			return false;

		//If object is not instance of videoobj return false
		if (!(thatObject instanceof VideoObj)) return false;

		//If object is the same then true
		if (thatObject == this) return true;

		//Create instance of object
		VideoObj v = (VideoObj) thatObject;

		//Check equality of parameters if video not in hashmap		  
		if (this._director.equals(v._director) && this._year == v._year && this._title.equals(v._title)) {
			return true;
		}
		return false;

	}

	/**
	 * Hashcode
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + this._title.hashCode();
		result = 37 * result + this._year;
		result = 37 * result + this._director.hashCode();
		return result;
	}

	/**
	 * Compares two videoobj and returns 0 if equal
	 */
	public int compareTo(Object thatObject) {

		VideoObj v = (VideoObj) thatObject;

		if (thatObject == null)
			throw new NullPointerException();

		if (!(thatObject instanceof VideoObj))
			throw new ClassCastException("Object is incompatible type");

		// Compare titles
		int titleDiff = this._title.compareTo(v._title);
		if (titleDiff != 0) return titleDiff;

		// Compare years
		int yearDiff = this._year - v._year;
		if (yearDiff != 0) return yearDiff; 

		// Compare directors
		int dirDiff = this._director.compareTo(v._director);
		if (dirDiff != 0) return dirDiff;

		return 0; //All fields are equal
	}

	/**
	 * Return string version
	 */
	public String toString() {
		String video = String.format("%s (%d) : %s", this._title, this._year, this._director);
		return video.trim(); 

	}
}
