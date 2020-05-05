package app;

public class App {

    int width;
    int height;
    int[][] game;

    public App(int width, int height){
        this.width = width;
        this.height = height;
        this.game = new int[width][height];
    }

    public void playGame(){
        System.out.println("---");
        for (int yAxis = 0; yAxis < height; yAxis++) {
            String row = "|";
            for (int xAxis = 0; xAxis < width; xAxis++) {
               if(this.game[xAxis][yAxis] ==0){
                   row += ".";
               }else{
                   row += "X";
               }
            }
            row += "|";
            System.out.println(row);
        }
        System.out.println("---");
    }

    public void alive( int x, int y){
    this.game[x][y] = 1;
    }

    public void dead(int x, int y) {
        this.game[x][y] = 0;
    }

    public int countAlive (int x, int y){
        int count = 0;

        count += returnStatus(x - 1,y-1);
        count += returnStatus(x, y - 1);
        count += returnStatus(x + 1, y - 1);

        count += returnStatus(x - 1, y);
        count += returnStatus(x + 1, y);

        count += returnStatus(x - 1, y + 1);
        count += returnStatus(x , y + 1);
        count += returnStatus(x + 1, y + 1);

        return count;
    }

    public int returnStatus (int x, int y){
        if(x < 0 || x>= width){
            return 0;
        }
        if(y < 0 || y>= height){
            return 0;
        }else{
        return this.game[x][y];
        }
    }

    public void map(){

        int[][] newGame = new int [width][height];

        for (int yAxis = 0; yAxis < height; yAxis++) {
            for (int xAxis = 0; xAxis < width; xAxis++) {
                int aliveNeighbors = countAlive(xAxis, yAxis);

                if(returnStatus(xAxis, yAxis) == 1){
                    if(aliveNeighbors < 2){
                        newGame[xAxis][yAxis] = 0;
                    }else if(aliveNeighbors == 2 || aliveNeighbors ==3){
                        newGame[xAxis][yAxis]=1;
                    }else if(aliveNeighbors >3){
                        newGame[xAxis][yAxis] = 0;
                    }
                }else{
                    if(aliveNeighbors ==3){
                        newGame[xAxis][yAxis] = 1;
                    }

                }
            }
        
        }
        this.game = newGame;
    }

    
    public void runGame(int number) {
        for (int i = 0; i < number; i++) {
            playGame();
            map();
        }

    }

    public static void gameSetUp (int xWidth, int yHeight, int livingPixels, int runCycles){

        App app = new App (xWidth, yHeight);

        for (int i = 0; i < livingPixels; i++) {
           int xAlive =( (int)(Math.random() * (xWidth)) );
            int yAlive = ( (int)(Math.random() * (yHeight)) );
            System.out.println(i);
            System.out.println(xAlive);
            System.out.println(yAlive);
            app.alive(xAlive, yAlive);
        }
        app.runGame(runCycles);
    }



        public static void main(String[] args) {

           gameSetUp(6,6,4,5);

        
        
        }

}