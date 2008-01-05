package fr.umlv.hmm2000.astar.priorityqueue;

import java.util.ArrayList;


/**
 * Class to reprensent priority queue with priority on the minimum value. The
 * priority queue is implemented with a heap and its methods of insertion and
 * removal.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class PriorityQueue {

  /*
   * Heap to manage the priority queue
   */
  private final ArrayList<PrioritizedElement> heap;

  /**
   * Constructor of an empty priority queue.
   * 
   */
  public PriorityQueue() {
    this.heap = new ArrayList<PrioritizedElement>();
  }

  /**
   * Insert a new element inthe heap and move it to its corect position
   * depending of its priority value.
   * 
   * @param p
   *            the element to insert.
   */
  public void insert(PrioritizedElement p) {

    // add the element in the heap
    this.heap.add(p);

    int i = this.heap.size() - 1;
    // while the element is misplaced
    while (i != 0
        && (this.heap.get(PriorityQueue.parentIndex(i)).getPriorityValue() >= this.heap
            .get(i).getPriorityValue())) {
      // swap it with its father
      this.swap(PriorityQueue.parentIndex(i), i);
      i = PriorityQueue.parentIndex(i);
    }
  }

  /**
   * Returns the element with the minimum priority value in the priority queue.
   * 
   * @return the element with the minimum priority value in the priority queue.
   */
  public PrioritizedElement extract() {

    // remove the fist element of the heap
    PrioritizedElement min = this.heap.remove(0);

    // if there is at least two elements
    if (this.heap.size() > 1) {

      // put the last element of teh heap in fosrt position
      this.swap(0, this.heap.size() - 1);

      int i = 0;
      boolean notAtCorrectIndex = true;
      // while the last element is misplaced
      while (notAtCorrectIndex) {

        // get the element with the minimum priority value between the
        // last element's sons and itself
        int j = this.minIndex(i);

        // if the last element is the minimum, it is correctly placed
        if (i == j) {
          notAtCorrectIndex = false;
        } else {
          // swap the last element with its minimum son
          this.swap(i, j);
          i = j;
        }
      }
    }

    // returns the min element
    return min;
  }

  /**
   * 
   * Change a priority value of an element in the priority queue. The new
   * priority value must be lower than the old priority value.
   * 
   * @param p
   *            the element to change priority value.
   * @param value
   *            the new priority value.
   */
  public void changeValue(PrioritizedElement p, double value) {
    if (p.getPriorityValue() > value) {
      // set the new priority value
      p.setPriorityValue(value);
      // replace the element depending its new priority value
      int i = this.heap.indexOf(p);
      while (i != 0
          && (this.heap.get(PriorityQueue.parentIndex(i)).getPriorityValue() >= this.heap
              .get(i).getPriorityValue())) {
        this.swap(PriorityQueue.parentIndex(i), i);
        i = PriorityQueue.parentIndex(i);
      }
    }
  }

  /**
   * Returns the index of the parent node of a node.
   * 
   * @param i
   *            the index of the node.
   * @return the index of the parent node.
   */
  private static int parentIndex(int i) {
    return ((i - 1) / 2);
  }

  /**
   * Returns the index of the left child of a node.
   * 
   * @param i
   *            the index of the node.
   * @return the index of the left child.
   */
  private static int leftChildIndex(int i) {
    return ((2 * i) + 1);
  }

  /**
   * Returns the index of the right child of a node.
   * 
   * @param i
   *            the index of the node.
   * @return the index of the right child.
   */
  private static int rightChildIndex(int i) {
    return ((2 * i) + 2);
  }

  /**
   * Returns the index of the prioritized element with the minimum priority
   * value between an element's sons and itself.
   * 
   * @param i
   *            index of the element.
   * @return the minimum index of the element with the minimum value.
   */
  private int minIndex(int i) {
    int j = i;
    // get the element with the minimum priority value between the
    // last element's sons and itself
    if (this.heap.size() > PriorityQueue.leftChildIndex(i)) {
      j = this.heap.get(i).getPriorityValue() < this.heap.get(
          PriorityQueue.leftChildIndex(i)).getPriorityValue() ? i
          : PriorityQueue.leftChildIndex(i);
    }
    if (this.heap.size() > PriorityQueue.rightChildIndex(i)) {
      j = this.heap.get(PriorityQueue.rightChildIndex(i)).getPriorityValue() < this.heap
          .get(j).getPriorityValue() ? PriorityQueue.rightChildIndex(i) : j;
    }
    return j;
  }

  /**
   * Swap two elements in the priority queue.
   * 
   * @param i
   *            the first element.
   * @param j
   *            the second element.
   */
  private void swap(int i, int j) {
    PrioritizedElement p1 = this.heap.get(i);
    PrioritizedElement p2 = this.heap.get(j);
    this.heap.set(i, p2);
    this.heap.set(j, p1);
  }

  /**
   * Returns if the priority queue is empty.
   * 
   * @return if the priority queue is empty
   */
  public boolean isEmpty() {
    return this.heap.isEmpty();
  }

  /**
   * Returns the size of the priority queue.
   * 
   * @return the size of the priority queue.
   */
  public int size() {
    return this.heap.size();
  }

  /**
   * Returns true if the element p is containing in the priority queue, or
   * false.
   * 
   * @param p
   *            the object.
   * @return true if the element p is containing in the priority queue, or
   *         false.
   */
  public boolean contains(PrioritizedElement p) {
    return this.heap.contains(p);
  }

  /**
   * Returns a string representation of the priority queue.
   * 
   * @return a string representation of the priority queue.
   */
  @Override
  public String toString() {
    return this.heap.toString();
  }

}
