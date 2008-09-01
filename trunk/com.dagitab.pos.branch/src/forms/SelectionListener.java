package forms;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

 public class SelectionListener implements ListSelectionListener {
        JTable table;
        int row = 0;
        private static Logger logger = Logger.getLogger(SelectionListener.class);
        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener(JTable table) {
            this.table = table;
        }
        public void valueChanged(ListSelectionEvent e) {
            // If cell selection is enabled, both row and column change events are fired
        	if (e.getSource() == table.getSelectionModel()
                  && table.getRowSelectionAllowed()) {
                // Column selection changed
                int first = e.getFirstIndex();
                int last = e.getLastIndex();
                //logger.info("selection changed true");
                //logger.info("row ="+ table.getSelectedRow());
                row = table.getSelectedRow();
                
            } 
            else if (e.getSource() == table.getColumnModel().getSelectionModel()
                   && table.getColumnSelectionAllowed() ){
                // Row selection changed
                int first = e.getFirstIndex();
                int last = e.getLastIndex();
                logger.info("row selection changed");
            }
    
            if (e.getValueIsAdjusting()) {
                // The mouse button has not yet been released
            }
            
        }
        public int getRow()
        {
        	return row;
        }
    }

