
public class Artist {

	private String stagename;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String PRIVATE_KEY;
	private String PUBLIC_KEY;
	
	
	public Artist(String stagename, String name, String surname, String email, String password) {
		super();
		this.stagename = stagename;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public void UploadSong(Song song) {
		//validate and save it to a file
		//and create a transaction
	}

	public String getStagename() {
		return stagename;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPublicKey() {
		return PUBLIC_KEY;
	}
}
