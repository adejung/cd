package ch.akros.workshop.cd.util;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

//@formatter:off
/**
 * 
 * 1. DONE Board has 5 places which can hold only stick (1-5) 
 * 2. Board has a bucket which has no limitation on how many stick it can hold (6) 
 * 3. DONE Done Try to put a stick on a specific number, if place is empty it returns 0, otherwise 2 sticks. 
 * 3a. DONE Put one in a empty Board shall return zero.
 * 3b. DONE Put a second one onto the same place return two.
 * 4. DONEvIf place was not empty it is afterwards as the stick was returned to the player.
 * 4a DONE Put 3rd times at the same space shall return 0.  
 * 5. DONE list the state of the board. (1-5), do not show internal state therefore remove.
 * 5a. DONE list size is 5
 * 5b. DONE new Board all places are empty
 * 6. DONE All places (1-5) shall behave the same
 * 
 */
//@formatter:on

@RunWith(CdiRunner.class)
public class BoardTest {
	@Inject
	private Board testee;

	@Test
	public void whenEmptyBoardAndPutInPlaceOneThenSticksReturnedZero() {
		int place = 1;
		assertReturnZeroOnFirstPlacement(place);
	}

	@Test
	public void whenEmptyBoardAndPutTwoInPlaceOneThenSticksReturnedTwo() {
		int place = 1;
		assertReturnTwoOnTwoPlacements(place);
	}

	@Test
	public void whenEmtpyBoardAndPutThreeTimesInPlaceOneThenSticksReturnedZero() {
		int place = 1;
		assertReturnZeroOnThreePlacements(place);

	}

	@Test
	public void ensureAllPlacesOneToFiveBehaveTheSameForFirstPlacement() {
		for (int i = 1; i < 6; i++) {
			assertReturnZeroOnFirstPlacement(i);
		}
	}

	@Test
	public void ensureAllPlacesOneToFiveBehaveTheSameForTwoPlacements() {
		for (int i = 1; i < 6; i++) {
			assertReturnTwoOnTwoPlacements(i);
		}
	}

	@Test
	public void ensureAllPlacesOneToFiveBehaveTheSameForThreePlacements() {
		for (int i = 1; i < 6; i++) {
			assertReturnZeroOnThreePlacements(i);
		}
	}

	private int putAStick(int place) {
		int stickReturned = testee.put(place);
		return stickReturned;
	}

	private void assertReturnZeroOnFirstPlacement(int place) {
		int stickReturned = putAStick(place);

		assertEquals("Returned sticks are not zero on place " + place, 0, stickReturned);
	}

	private void assertReturnTwoOnTwoPlacements(int place) {
		testee.put(place);
		int stickReturned = putAStick(place);

		assertEquals("Returned sticks are not two on place " + place, 2, stickReturned);
	}

	private void assertReturnZeroOnThreePlacements(int place) {
		testee.put(place);
		testee.put(place);
		int stickReturned = putAStick(place);

		assertEquals("Returned sticks after putting 3 sticks are not zero on place " + place, 0, stickReturned);
	}

}