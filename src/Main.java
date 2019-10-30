import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();

        int dim=10;
        int matrix[][] = new int[10][10];

        JFrame f = new JFrame("Window containing a matrix");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(dim, dim));

        for(int r = 0; r < dim; r++){
            for(int c = 0; c < dim; c++){
                ChangingButton button= new ChangingButton(r, c, matrix);
                p.add(button);
            }
        }
        f.add(p);
        f.pack();
        f.setVisible(true);

    }

    public static class ChangingButton extends JButton {

        private final int[][] fModel;
        private final int fX;
        private final int fY;

        public ChangingButton(final int x, final int y, final int[][] model) {
            fX= x;
            fY= y;
            fModel= model;

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fModel[fX][fY] = fModel[fX][fY] == 1 ? 0 : 1;
                    updateNameFromModel();
                }
            });
            updateNameFromModel();
        }

        private void updateNameFromModel() {
            setText(String.valueOf(fModel[fX][fY]));
        }

    }

}
