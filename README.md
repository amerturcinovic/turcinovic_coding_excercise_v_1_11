# turcinovic coding excercise Live Score Board

## Simple library implementation for Score Board of World Cup

## Interface / API has 4 methods

- Add or start match - start new match
  - Exception ```IllegalArgumentException``` is thrown if arguments are wrong or match already started
- Finish match in progress - finish match in progress
  - Exception ```IllegalArgumentException``` is thrown if arguments are wrong or if there is no match on score board
- Update match with new absolute score - update match score result
-   - Exception ```IllegalArgumentException``` is thrown if arguments are wrong
- Get score board ranking - get all match in progress ordered by most scores of both teams or most recent match

## Installation

Instructions on how to compile and create JAR file.

### Clone the repository
```bash
git clone https://github.com/amerturcinovic/turcinovic_coding_excercise_v_1_11.git
```

### Navigate to the project directory
```bash
cd turcinovic_coding_excercise_v_1_11
```
### Compile java files to class files
```bash
javac -d ./out -cp score-board-excercise.jar \
src/api/*.java \
src/impl/models/*.java \
src/impl/persistence/*.java \
src/impl/FootballWorldCupScoreBoard.java
```
This will create *.class file in ./out directory

### Create *.jar files
```bash
jar cvf ./out/score_board_lib.jar ./out/api/*.class \
./out/impl/models/*.class \
./out/impl/persistance/*.class \
./out/impl/FootballWorldCupScoreBoard.class
```
This will create score_board_lib.jar in ./out directory


## Example usage
### Default implementation
```java
class Scratch {
    public static void main(String[] args) {
        TrackableScoreBoard scoreBoard = new FootballWorldCupScoreBoard();
        scoreBoard.startMatch("BRAZIL", "ARGENTINA");
        scoreBoard.updateMatch(
                new MatchInfo("BRAZIL", 1, "ARGENTINA", 0)
        );
        List<MatchInfo> boardRanking = scoreBoard.getBoardRanking();
    }
}
```
### Implementation with custom repository for score board
```java
class Scratch {
    public static void main(String[] args) {
        TrackableScoreBoard scoreBoard = new FootballWorldCupScoreBoard(
                new SimpleOrderedInMemoryCollection()
        );
        scoreBoard.startMatch("BRAZIL", "ARGENTINA");
        List<MatchInfo> boardRanking = scoreBoard.getBoardRanking(); // to get list of matches
        System.out.println(boardRanking); // will call .toString() and return as: 1. BRAZIL 0 - ARGENTINA 0 
    }
}
```

### Notes and information for project
- Implemented as simple java library project
- Implementation is covered by test cases
- It is very simple to change sorting of score board or persistence of score board,
as everything is modular and easy to change with different implementation,
just implement interfaces ```Repository``` and ```Sortable```
- Default implementation use ordered ```Collection LinkedHashMap```
- But it is easy to change to some concurrent thread safe implementation
- Minimum data are stored only teams name and scores
we could also design interface / API that provide exact start of match
and that could be also one of sorting criteria