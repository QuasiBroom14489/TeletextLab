/**
 * Implements the doubly-linked list of messages for Teletext
 */

import java.awt.Graphics;

public class TeletextList
{
  private ListNode2 heading, topNode;

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order.  Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines)
  {
    // add fist Node "Heading" and two spaces above and below
    heading = new ListNode2("HeadLines: ", null, null);
    heading.setNext(heading);
    heading.setPrevious(heading);
    addBefore(heading, " ");


    for(int i = headlines.length-1; i>=0; i--){
      addAfter(heading, headlines[i]);
    }

    addAfter(heading, " ");
    topNode = heading.getNext().getNext();

  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg)
  {
    addBefore(heading.getNext().getNext(), msg);

  }

  /**
   * Deletes the node that follows topNode from the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete()
  {
    ListNode2 node = topNode.getNext();
    if(node != heading && node!= heading.getPrevious() && node!=heading.getNext()){
      remove(node);
    }

  }

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp()
  {
    topNode = topNode.getNext();

  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a referenece to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg)
  {
    ListNode2 newNode = new ListNode2(msg, node.getPrevious(), node);
    node.getPrevious().setNext(newNode);
    node.setPrevious(newNode);
    return newNode;
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a referenece to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg)
  {
    ListNode2 newNode = new ListNode2(msg, node, node.getNext());
    node.getNext().setPrevious(newNode);
    node.setNext(newNode);

    return newNode;
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node)
  {
    ListNode2 lst = heading.getNext().getNext();

    while(lst != heading){
      if(lst == node){
        lst.getPrevious().setNext(lst.getNext());
        lst.getNext().setPrevious(lst.getPrevious());
        break;
      }
      lst = lst.getNext();
    }



  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines)
  {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++)
    {
      g.drawString((String)node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
}
