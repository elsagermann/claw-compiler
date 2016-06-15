/*
 * This file is released under terms of BSD license
 * See LICENSE file for more information
 */

package cx2x.xcodeml.xnode;

import org.w3c.dom.Element;

/**
 * The XfunctionDefinition represents the FfunctionDefinition (5.3) element in XcodeML
 * intermediate representation.
 *
 * Elements: (name, symbols?, params?, declarations?, body)
 * - Required:
 *   - name (text)
 *   - body (Xbody)
 * - Optional:
 *   - symbols (XsymbolTable)
 *   - params  (Xparams)
 *   - declarations (XdeclTable)
 *
 * Can have lineno and file attributes (XenhancedElement)
 *
 * @author clementval
 */

public class XfunctionDefinition extends Xnode {

  // Elements
  private XsymbolTable _symbolTable = null;
  private Xnode _params = null;
  private XdeclTable _declTable = null;
  private Xnode _body = null;
  private Xnode _name = null;

  /**
   * Xelement standard ctor. Pass the base element to the base class and read
   * inner information (elements and attributes).
   * @param baseElement The root element of the Xelement
   */
  public XfunctionDefinition(Element baseElement){
    super(baseElement);
    Xnode symbols = find(Xcode.SYMBOLS);
    _symbolTable = (symbols != null) ? new XsymbolTable(symbols.getElement())
        : null;
    Xnode declarations = find(Xcode.DECLARATIONS);
    _declTable = (declarations != null) ?
        new XdeclTable(declarations.getElement()) : null;
    _params = find(Xcode.PARAMS);
    _body = find(Xcode.BODY);
    _name = find(Xcode.NAME);
  }

  /**
   * Get the function's symbols table.
   * @return A XsymbolTable object containing the function's symbols.
   */
  public XsymbolTable getSymbolTable(){
    return _symbolTable;
  }

  /**
   * Get the function's declarations table.
   * @return A XdeclTable object containing the function's declarations.
   */
  public XdeclTable getDeclarationTable(){
    return _declTable;
  }

  /**
   * Get the function's body.
   * @return A Xbody object for the function.
   */
  public Xnode getBody(){
    return _body;
  }

  /**
   * Get the function name.
   * @return Name of the function as an Xname object.
   */
  public Xnode getName(){
    return _name;
  }

  /**
   * Get the parameters list.
   * @return Parameters list.
   */
  public Xnode getParams(){
    return _params;
  }

  /**
   * Create an identical copy of the current function definition.
   * @return A new XfunctionDefinition object that is the clone of this function definition.
   */
  public XfunctionDefinition cloneObject(){
    Element clone = (Element)cloneNode();
    return new XfunctionDefinition(clone);
  }
}