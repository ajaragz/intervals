package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervalOpenOpenlTestEndpoints() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervalClosedOpenlTestEndpoints() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenClosedTestEndpoints() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervalClosedClosedTestEndpoints() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void given2NonIntersectingOpenOpenIntervalsTestIntersectsThenFalse() {
    Interval first = new IntervalBuilder().open(left.getLess()).open(right.getEquals()).build();
    Interval second = new IntervalBuilder().open(right.getEquals()).open(right.getGreater()).build();
    assertFalse(first.intersects(second));
    assertFalse(second.intersects(first));
  }

  @Test
  public void given2SameOpenOpenIntervalsTestIntersectThenTrue() {
    Interval interval = new IntervalBuilder().open(left.getEquals()).open(right.getGreater()).build();
    Interval sameInterval = new IntervalBuilder().open(left.getEquals()).open(right.getGreater()).build();
    assertTrue(interval.intersects(sameInterval));
    assertTrue(sameInterval.intersects(interval));
  }

  @Test
  public void given2OpenOpenIntervalsOneInsideTheOtherTestIntersectThenTrue() {
    Interval outsideInterval = new IntervalBuilder().open(left.getLess()).open(right.getGreater()).build();
    Interval insideInterval = new IntervalBuilder().open(left.getGreater()).open(right.getLess()).build();
    assertTrue(insideInterval.intersects(outsideInterval));
    assertTrue(outsideInterval.intersects(insideInterval));
  }

  @Test
  public void given2OpenOpenIntervalsOnePartiallyIntersectingTheOtherThenTrue() {
    Interval leftInterval = new IntervalBuilder().open(left.getLess()).open(right.getLess()).build();
    Interval rightInterval = new IntervalBuilder().open(left.getGreater()).open(right.getGreater()).build();
    assertTrue(leftInterval.intersects(rightInterval));
    assertTrue(rightInterval.intersects(leftInterval));
  }

  @Test
  public void given2NonIntersectingClosedClosedIntervalsTestIntersectsThenFalse() {
    Interval first = new IntervalBuilder().closed(left.getLess()).closed(right.getEquals()).build();
    Interval second = new IntervalBuilder().closed(right.getGreater()).closed(right.getGreater()).build();
    assertFalse(first.intersects(second));
    assertFalse(second.intersects(first));
  }

  @Test
  public void given2SameClosedClosedIntervalsTestIntersectThenTrue() {
    Interval interval = new IntervalBuilder().closed(left.getEquals()).closed(right.getEquals()).build();
    Interval sameInterval = new IntervalBuilder().closed(left.getEquals()).closed(right.getEquals()).build();
    assertTrue(interval.intersects(sameInterval));
    assertTrue(sameInterval.intersects(interval));
  }

  @Test
  public void given2ClosedClosedIntervalsOneInsideTheOtherTestIntersectThenTrue() {
    Interval outsideInterval = new IntervalBuilder().closed(left.getLess()).closed(right.getGreater()).build();
    Interval insideInterval = new IntervalBuilder().closed(left.getGreater()).closed(right.getLess()).build();
    assertTrue(insideInterval.intersects(outsideInterval));
    assertTrue(outsideInterval.intersects(insideInterval));
  }

  @Test
  public void given2ClosedClosedIntervalsOnePartiallyIntersectingTheOtherThenTrue() {
    Interval leftInterval = new IntervalBuilder().closed(left.getLess()).closed(right.getLess()).build();
    Interval rightInterval = new IntervalBuilder().closed(left.getGreater()).closed(right.getGreater()).build();
    assertTrue(leftInterval.intersects(rightInterval));
    assertTrue(rightInterval.intersects(leftInterval));
  }

}