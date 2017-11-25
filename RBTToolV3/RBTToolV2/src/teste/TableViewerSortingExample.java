package teste;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

/**
* Table sorting example using label provider
*
* @author cabler
*
*/
public class TableViewerSortingExample {

  private class MyContentProvider implements IStructuredContentProvider {

          public Object[] getElements(Object inputElement) {
                  return (Person[]) inputElement;
          }

          public void dispose() {
          }

          public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
          }
  }

  private class MyLabelProvider implements ITableLabelProvider {

          @Override
          public Image getColumnImage(Object element, int columnIndex) {
                  // TODO Auto-generated method stub
                  return null;
          }

          @Override
          public String getColumnText(Object element, int columnIndex) {
                  if (!(element instanceof Person)) {
                          return null;
                  }
                  Person p = (Person) element;
                  switch (columnIndex) {
                          case 0:
                          return p.givenname;
                          case 1:
                          return p.surname;
                          case 2:
                          return p.email;
                  }
                  return "Error";
          }

          @Override
          public void addListener(ILabelProviderListener listener) {
                  // TODO Auto-generated method stub

          }

          @Override
          public void dispose() {
                  // TODO Auto-generated method stub

          }

          @Override
          public boolean isLabelProperty(Object element, String property) {
                  // TODO Auto-generated method stub
                  return false;
          }

          @Override
          public void removeListener(ILabelProviderListener listener) {
                  // TODO Auto-generated method stub

          }

  }

  public class Person {
          public String givenname;
          public String surname;
          public String email;

          public Person(String givenname, String surname, String email) {
                  this.givenname = givenname;
                  this.surname = surname;
                  this.email = email;
          }

  }

  public TableViewerSortingExample(Shell shell) {
          final TableViewer viewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
          viewer.setContentProvider(new MyContentProvider());
          viewer.setLabelProvider(new MyLabelProvider());
          String[] colNames = new String[] { "Givenname", "Surname", "Email" };

          int i = 0;
          for (String name : colNames) {
                  final int colIdx = i;
                  TableColumn column = new TableColumn(viewer.getTable(), SWT.NONE);
                  column.setWidth(200);
                  column.setText(name);
                  column.setMoveable(true);

                  TableColumnSorter cSorter = new TableColumnSorter(viewer, column) {
                          protected int doCompare(Viewer v, Object e1, Object e2) {
                                  ITableLabelProvider lp = ((ITableLabelProvider) viewer
                                  .getLabelProvider());
                                  String t1 = lp.getColumnText(e1, colIdx);
                                  String t2 = lp.getColumnText(e2, colIdx);
                                  return t1.compareTo(t2);
                          }
                  };

                  cSorter.setSorter(cSorter, TableColumnSorter.ASC);
                  i++;
          }

          Person[] model = createModel();
          viewer.setInput(model);
          viewer.getTable().setLinesVisible(true);
          viewer.getTable().setHeaderVisible(true);

  }

  private Person[] createModel() {
          Person[] elements = new Person[4];
          elements[0] = new Person("Tom", "Schindl",
          "tom.schindl@bestsolution.at");
          elements[1] = new Person("Boris", "Bokowski",
          "Boris_Bokowski@ca.ibm.com");
          elements[2] = new Person("Tod", "Creasey", "Tod_Creasey@ca.ibm.com");
          elements[3] = new Person("Wayne", "Beaton", "wayne@eclipse.org");

          return elements;
  }

  private static abstract class TableColumnSorter extends ViewerComparator {
          public static final int ASC = 1;

          public static final int NONE = 0;

          public static final int DESC = -1;

          private int direction = 0;

          private TableColumn column;

          private TableViewer viewer;

          public TableColumnSorter(TableViewer viewer, TableColumn column) {
                  this.column = column;
                  this.viewer = viewer;
                  this.column.addSelectionListener(new SelectionAdapter() {

                          public void widgetSelected(SelectionEvent e) {
                                  if (TableColumnSorter.this.viewer.getComparator() != null) {
                                          if (TableColumnSorter.this.viewer.getComparator() == TableColumnSorter.this) {
                                                  int tdirection = TableColumnSorter.this.direction;

                                                  if (tdirection == ASC) {
                                                          setSorter(TableColumnSorter.this, DESC);
                                                  } else if (tdirection == DESC) {
                                                          setSorter(TableColumnSorter.this, NONE);
                                                  }
                                          } else {
                                                  setSorter(TableColumnSorter.this, ASC);
                                          }
                                  } else {
                                          setSorter(TableColumnSorter.this, ASC);
                                  }
                          }
                  });
          }

          public void setSorter(TableColumnSorter sorter, int direction) {
                  if (direction == NONE) {
                          column.getParent().setSortColumn(null);
                          column.getParent().setSortDirection(SWT.NONE);
                          viewer.setComparator(null);
                  } else {
                          column.getParent().setSortColumn(column);
                          sorter.direction = direction;

                          if (direction == ASC) {
                                  column.getParent().setSortDirection(SWT.DOWN);
                          } else {
                                  column.getParent().setSortDirection(SWT.UP);
                          }

                          if (viewer.getComparator() == sorter) {
                                  viewer.refresh();
                          } else {
                                  viewer.setComparator(sorter);
                          }

                  }
          }

          public int compare(Viewer viewer, Object e1, Object e2) {
                  return direction * doCompare(viewer, e1, e2);
          }

          protected abstract int doCompare(Viewer TableViewer, Object e1, Object e2);
  }

  /**
  * @param args
  */
  public static void main(String[] args) {
          Display display = new Display();

          Shell shell = new Shell(display);
          shell.setLayout(new FillLayout());
          new TableViewerSortingExample(shell);
          shell.open();

          while (!shell.isDisposed()) {
                  if (!display.readAndDispatch())
                  display.sleep();
          }

          display.dispose();

  }

}