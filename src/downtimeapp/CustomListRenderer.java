/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author natha_000
 */
public class CustomListRenderer extends DefaultListCellRenderer{
    public Component getListCellRendererComponent(JList<?> list,
                                Object value,
                                int index,
                                boolean isSelected,
                                boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Room) {
            Room room = (Room)value;
            setText(room.name);
        }
        if(value instanceof Building){
            Building build = (Building) value;
            setText(build.name);
        }
        return this;
    }
}
