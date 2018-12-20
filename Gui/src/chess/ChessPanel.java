package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChessPanel extends JPanel {
    private int space=20;
    private int grids=30;
    private int radius = space/2;

    private int[][] chesses=new int[grids+1][grids+1];
    private int currcolor=1;

    private JMenuBar chessMenuBar=new JMenuBar();
    private JMenu optMene=new JMenu("操作");
    private JMenuItem startMenuItem=new JMenuItem("开始");
    private JMenuItem exitMenuItem=new JMenuItem("退出");

    private ActionListener startHandle=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearGrids();
            currcolor=1;
            repaint();
        }
    };

    private ActionListener exitHandle=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private MouseListener playChessHandler=new MouseAdapter() {
        public void mouseClicked(MouseEvent e){
            int x=e.getX();
            int y=e.getY();

            if(x<=grids*space&&x>=0&&y<=grids*space&&y>=0)
                if(chesses[round(x)][round(y)]==0){
                    chesses[round(x)][round(y)]=currcolor;
                    currcolor=currcolor==1?2:1;
                    repaint();
                }
        }
    };

    private int round(float a){
        float f=a/space;
        return Math.round(f);
    }

    public ChessPanel(int space,int grids){
        this.space=space;
        this.grids=grids;
        this.radius=space/2;

        setBackground(Color.yellow);
        setSize(space*grids,space*grids);
        startMenuItem.addActionListener(startHandle);
        exitMenuItem.addActionListener(exitHandle);
        addMouseListener(playChessHandler);

        chessMenuBar.add(optMene);
        optMene.add(startMenuItem);
        optMene.add(exitMenuItem);
    }

    public JMenuBar getMenuBar(){
        return chessMenuBar;
    }

    private void drawChess(Graphics g,int x,int y,int color){
        g.setColor(color==1?Color.WHITE:Color.BLACK);
        g.fillOval(x*space-radius,y*space-radius,radius*2,radius*2);
    }

    private void drawGrids(Graphics g){
        g.setColor(Color.DARK_GRAY);
        for(int i=0;i<=grids;i++){
            g.drawLine(0,i*space,grids*space,i*space);
            g.drawLine(i*space,0,i*space,grids*space);
        }
    }

    private void clearGrids() {
        for(int i=0;i<=grids;i++)
            for(int j=0;j<=grids;j++)
                chesses[i][j]=0;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawGrids(g);
        for(int i=0;i<=grids;i++)
            for(int j=0;j<=grids;j++)
                if(chesses[i][j]!=0)
                    drawChess(g,i,j,chesses[i][j]);
    }
}

