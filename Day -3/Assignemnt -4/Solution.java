class Solution {
	public Team[] sort(Team[] teams) {
		for (int i = 0; i < teams.length; i++) {
			int max = i;
			for (int j = i + 1; j < teams.length; j++) {
				if (teams[max].compareTo(teams[j]) == -1) {
					max = j;
				}
			}
			Team temp = teams[i];
			teams[i] = teams[max];
			teams[max] = temp;
		}
		return teams;
	}
}
