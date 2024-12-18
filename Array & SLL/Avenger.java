/**
 * This class acts as the blueprint for avenger objects. It contains
 * various useful methods such as toString and compareTo, as well as an equals method.
 * @author aasher
 *
 */
public class Avenger implements Comparable<Avenger> {
	private String heroAlias;
	private String heroName;
	private String performer;

	private int nameFreq;
	private int aliasFreq;
	private int performerFreq;
	
	public String getHeroAlias() {
		return heroAlias;
	}

	public String getHeroName() {
		return heroName;
	}

	public String getPerformer() {
		return performer;
	}

	public int getNameFreq() {
		return nameFreq;
	}

	public int getAliasFreq() {
		return aliasFreq;
	}

	public int getPerformerFreq() {
		return performerFreq;
	}
	
    public void setHeroAlias(String heroAlias) {
		this.heroAlias = heroAlias;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public void setNameFreq(int nameFreq) {
		this.nameFreq = nameFreq;
	}

	public void setAliasFreq(int aliasFreq) {
		this.aliasFreq = aliasFreq;
	}

	public void setPerformerFreq(int performerFreq) {
		this.performerFreq = performerFreq;
	}

	public void incrementAliasFreq() {
		this.aliasFreq++;
	}
	
	public void incrementNameFreq() {
		this.nameFreq++;
	}
	
	public void incrementPerformerFreq() {
		this.performerFreq++;
	}
	

	public Avenger(String heroAlias, String heroName, String performer) {
        this.heroAlias = heroAlias;
        this.heroName = heroName;
        this.performer = performer;
        this.nameFreq = 0;
        this.aliasFreq = 0;
        this.performerFreq = 0;
    }
	
	// Increment frequency counts
    public void incrementCounts() {
        this.nameFreq++;
        this.aliasFreq++;
        this.performerFreq++;
    }
    
    /**
     * Basic toString method for output format.
     */
    @Override
    public String toString() {
		
    	String format = heroAlias + " aka " + heroName
    			+ " performed by " + performer
    			+ " mentioned "
    			+ "(n: " + nameFreq
    			+ " + a: " + aliasFreq
    			+ " + p: " + performerFreq
    			+ ")" + " time(s)";
    	
    	return format;
    	
    }
    

	

	@Override
public int compareTo(Avenger other) {
    return this.getHeroAlias().toLowerCase().compareTo(other.getHeroAlias().toLowerCase());
}

	
	/**
	 * Checks if two avengers have the same alias / are the same.
	 * @param other
	 * @return
	 */
	public boolean equals(Avenger other) {
		
		boolean isEqual = false;
		if (this.getHeroAlias() == other.getHeroAlias()) {
			isEqual = true;
		}
		
		return isEqual;
	}
	
	
	
}
