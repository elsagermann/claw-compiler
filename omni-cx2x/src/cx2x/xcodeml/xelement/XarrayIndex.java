/*
 * This file is released under terms of BSD license
 * See LICENSE file for more information
 */

package cx2x.xcodeml.xelement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The XarrayIndex represents the arrayIndex (8.10) element in XcodeML
 * intermediate representation.
 *
 * Elements:
 * - Required:
 *   - exprModel (XbaseElement)
 *
 * @author clementval
 */

public class XarrayIndex extends XbaseElement {
  private XexprModel _exprModel;

  /**
   * Xelement standard ctor. Pass the base element to the base class and read
   * inner information (elements and attributes).
   * @param baseElement The root element of the Xelement
   */
  public XarrayIndex(Element baseElement){
    super(baseElement);
    readElementInformation();
  }

  /**
   * Read inner element information.
   */
  private void readElementInformation(){
    // Find Var element if there is one
    // TODO move to XexprModel
    Xvar var = XelementHelper.findVar(this, false);
    if(var != null){
      _exprModel = new XexprModel(var);
    }
  }

  /**
   * Get the inner exprModel object.
   * @return The inner exprModel object.
   */
  public XexprModel getExprModel(){
    return _exprModel;
  }

  /**
   * Create an empty arrayIndex element in the given program
   */
  public static XarrayIndex createEmpty(XcodeProg xcodeml){
    Element arrayIndex = xcodeml.getDocument().
      createElement(XelementName.ARRAY_INDEX);
    return new XarrayIndex(arrayIndex);
  }

  /**
   * Append a XbaseElement as the last children of XarrayIndex.
   * @param element The element to append.
   */
  public void append(XbaseElement element){
    append(element, false);
  }

  /**
   * Append a XbaseElement as the last children of XarrayIndex.
   * @param element       The element to append.
   * @param cloneElement  If true, the element is cloned before being added. If
   *                      false, the element is directly added.
   */
  public void append(XbaseElement element, boolean cloneElement){
    if(cloneElement){
      Node clone = element.clone();
      baseElement.appendChild(clone);
    } else {
      baseElement.appendChild(element.getBaseElement());
    }
  }

}