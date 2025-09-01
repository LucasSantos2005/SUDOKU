import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Sudoku {
    class Tile extends JButton{
        int r;
        int c;
        Tile(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    int boardWidth = 600;
    int boardHeight = 650;

    String[] puzzle = {
"--74916-5",
"2---6-3-9",
"-----7-1-",
"-586----4",
"--3----9-",
"--62--187",
"9-4-7---2",
"67-83----",
"81--45---"
};

String[] solução = {
"387491625",
"241568379",
"569327418",
"758619234",
"123784596",
"496253187",
"934176852",
"675832941",
"812945763"
};

    JFrame frame = new JFrame("Sudoku");
    JLabel texJLabel = new JLabel();
    JPanel texJPanel = new JPanel();
    JPanel paineltabuleiro = new JPanel();
    JPanel botoesPanel = new JPanel();

    JButton numeroselecionado = null;
    int erros = 0;


    Sudoku(){
        
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        texJLabel.setFont(new Font("arial", Font.BOLD, 30));
        texJLabel.setHorizontalAlignment(JLabel.CENTER);
        texJLabel.setText("Sudoku erros: = 0");

        texJPanel.add(texJLabel);
        frame.add(texJPanel, BorderLayout.NORTH);

        paineltabuleiro.setLayout(new GridLayout(9,9));
        configTiles();
        frame.add(paineltabuleiro, BorderLayout.CENTER);

        botoesPanel.setLayout(new GridLayout(1, 9));
        configbotao();
        frame.add(botoesPanel,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    boolean jogoCompleto() {
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            Component comp = paineltabuleiro.getComponent(r * 9 + c);
            if (comp instanceof Tile) {
                Tile tile = (Tile) comp;
                String texto = tile.getText();
                String correto = String.valueOf(solução[r].charAt(c));

                if (!texto.equals(correto)) {
                    return false; // ainda tem espaço vazio ou errado
                }
            }
        }
    }
    return true; // tudo certo!
}

    void configTiles(){
        for (int r = 0; r <9; r++){
            for ( int c = 0; c < 9; c++){
                Tile tile = new Tile(r, c);
                char tileChar = puzzle[r].charAt(c);
                if ((r == 2 && c == 2) || (r == 2 && c ==5) || (r ==5 && c == 2)|| (r == 5 && c == 5))   {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,5,5, Color.BLACK));
                }
                if (tileChar != '-') {
                    tile.setFont(new Font("arial", Font.BOLD, 20));
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(Color.lightGray);              
                }
                  else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.white);
                  }
                   if ((r == 2 && c == 2) || (r == 2 && c == 5) || (r == 5 && c == 2) || (r == 5 && c == 5)) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.black));
                }

                else if (r == 2 || r == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,5,1, Color.BLACK));
                }
                else if (c == 2 || c == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,1,5, Color.BLACK));
                }
                else {
                    tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }

                tile.setFocusable(false);
                paineltabuleiro.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numeroselecionado != null) {
                            if (tile.getText()!= ""){
                                return;
                            }
                            String numeroselecionadoText = numeroselecionado.getText();
                            String tileSolution = String.valueOf(solução[r].charAt(c));
                            if (tileSolution.equals(numeroselecionadoText)) {
                                tile.setText(numeroselecionadoText);
                                if (jogoCompleto()) {
        JOptionPane.showMessageDialog(frame, "🎉 Parabéns, você completou o Sudoku!");
    
}
                            }
                            else {
                                erros += 1;
                                texJLabel.setText("Sudoku: " + String.valueOf(erros));
                            }
                        }
                    }
                });
            }
        }
    }

    void configbotao(){
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setBackground(Color.white);
            botoesPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    if (numeroselecionado != null){
                        numeroselecionado.setBackground(Color.white);
                    }
                    numeroselecionado = button;
                    numeroselecionado.setBackground(Color.lightGray);
                }
            });
        }
    }

}
