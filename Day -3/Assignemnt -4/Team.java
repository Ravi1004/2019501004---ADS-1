public class Team implements Comparable<Team> {
    public String teamName;
    public int noOfWins;
    public int noOfLosses;
    public int noOfDraws;

    public Team(String name, int wins, int losses, int draws) {
        this.teamName = name;
        this.noOfDraws = draws;
        this.noOfWins = wins;
        this.noOfLosses = losses;
    }

    public String toString() {
        // retrun all the attributes as a string but appending with ", "
        return this.teamName + "," + this.noOfWins + "," + this.noOfLosses + "," + this.noOfDraws;
    }

    public int compareTo(Team that) {
        if (this.noOfWins > that.noOfWins) {
            return 1;
        } else if (this.noOfWins < that.noOfWins) {
            return -1;
        } else {
            if (this.noOfLosses < that.noOfLosses) {
                return 1;
            } else if (this.noOfLosses > that.noOfLosses) {
                return -1;
            } else {
                if (this.noOfDraws > that.noOfDraws) {
                    return 1;
                } else if (this.noOfDraws < that.noOfDraws) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}