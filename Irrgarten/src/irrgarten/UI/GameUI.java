
package irrgarten.UI;
import irrgarten.Directions;
import irrgarten.GameState;
import irrgarten.UI.Cursors;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author flo
 */
public class GameUI extends JFrame implements UI {
    
    private Cursors cursors = new Cursors (this,true);

    private static GameUI instance = null;
    public static GameUI getInstance(){
        if ( instance == null){
            instance = new GameUI();
        }
        return instance;
    }
    
  
    
    public GameUI(){
        setVisible(true);
        initComponents();
        labyrinthTextArea.setColumns(15);
        labyrinthTextArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 19)); // Ajusta el tamaño y tipo de fuente
        labyrinthTextArea.setRows(9);
        labyrinthTextArea.setLineWrap(false); // Evita dividir palabras
        labyrinthTextArea.setWrapStyleWord(false);
        titulo_monsters.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 19)); // Ajusta el tamaño y tipo de fuente
        titulo_players.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 19)); // Ajusta el tamaño y tipo de fuente
        titulo_players.setText("PLAYERS: ");
        titulo_monsters.setText("MONSTERS: ");
        cursors = new Cursors (this,true);
    }
    
    @Override
    public Directions nextMove() {
        cursors.setVisible(true);
        return cursors.getDirections();
    }

    @Override
    public void showGame(GameState gameState) {
        // Actualizar los componentes gráficos con la información del GameState
        labyrinthTextArea.setText(gameState.getLabyrinth());
        playersTextArea.setText(gameState.getPlayers());
        monstersTextArea.setText(gameState.getMonsters());
        loog.setText(gameState.getLog());
        currentPlayerLabel.setText("Turno del Jugador: " + gameState.getCurrentPlayer());

        if (gameState.getWinner()) {
            JOptionPane.showMessageDialog(null,"¡Felicidades! ¡Has ganado jugador nº " + gameState.getCurrentPlayer() + "!");
            //winnerLabel.setText("¡Felicidades! ¡Has ganado jugador nº " + gameState.getCurrentPlayer() + "!");
        } else {
            winnerLabel.setText("");
        }

        // Repintar la interfaz gráfica para asegurar la actualización
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        labyrinthTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        playersTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        monstersTextArea = new javax.swing.JTextArea();
        currentPlayerLabel = new javax.swing.JLabel();
        winnerLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        loog = new javax.swing.JTextArea();
        titulo_monsters = new javax.swing.JLabel();
        titulo_players = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PANEL");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labyrinthTextArea.setColumns(15);
        labyrinthTextArea.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        labyrinthTextArea.setRows(9);
        labyrinthTextArea.setText("LABERINTO\n");
        jScrollPane2.setViewportView(labyrinthTextArea);

        playersTextArea.setColumns(20);
        playersTextArea.setRows(5);
        playersTextArea.setText("Players");
        jScrollPane4.setViewportView(playersTextArea);

        monstersTextArea.setColumns(20);
        monstersTextArea.setRows(5);
        monstersTextArea.setText("Monsters");
        jScrollPane5.setViewportView(monstersTextArea);

        currentPlayerLabel.setText("currentPlayerLabel");

        winnerLabel.setText("winnerLabel");

        loog.setColumns(20);
        loog.setRows(5);
        loog.setText("LOG");
        jScrollPane6.setViewportView(loog);

        titulo_monsters.setText("jLabel1");

        titulo_players.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(titulo_monsters)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titulo_players)
                .addGap(349, 349, 349))
            .addGroup(layout.createSequentialGroup()
                .addGap(610, 610, 610)
                .addComponent(currentPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(winnerLabel)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(556, 556, 556)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(titulo_players)
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_monsters)
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(currentPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(winnerLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentPlayerLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextArea labyrinthTextArea;
    private javax.swing.JTextArea loog;
    private javax.swing.JTextArea monstersTextArea;
    private javax.swing.JTextArea playersTextArea;
    private javax.swing.JLabel titulo_monsters;
    private javax.swing.JLabel titulo_players;
    private javax.swing.JLabel winnerLabel;
    // End of variables declaration//GEN-END:variables
}