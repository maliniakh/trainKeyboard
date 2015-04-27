### trainKeyboard
A solution for a following problem:

> _The machine has a touchscreen display which works as follows. As the user types each character of the station’s name on the touchscreen, the display should update to show all valid choices for the next character and a list of possible matching stations.
For example when ‘D A R T’ has been entered, the code should return, say, ["DARTFORD", "DARTMOUTH"] along with letters ['F', 'M']_

The solution uses a trie-like data structure to get the results. The trie structure is built on the initialization only, the results are computed at O(log n) complexity. Information within the trie is redundant, i.e. every node keeps track of all cities in the nodes below it. That means the result is ready upon reaching any given node and no additional traversing is needed. The effect is that the time complexity is lower at the expense of the space complexity.

#### Running

_mvn test_

#### Requirements

Java 6 or newer
