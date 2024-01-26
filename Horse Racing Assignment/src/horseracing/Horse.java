package horseracing;

public class Horse{
        private String name;
        private int mudRating;
        private int grassRating;
        private int dirtRating;
        private int airRating;
        private int paradisusRating;
        private int kitchenRating;
        private double preferredLength;

        private int currentPosition;
        private boolean finishedRace;
        private int number;
    
        //constructor
        public Horse(String name, int mudRating, int grassRating, int dirtRating,int airRating,int paradisusRating,int kitchenRating, double preferredLength) {
            
            //setting attributes
            this.name = name;
            this.mudRating = mudRating;
            this.grassRating = grassRating;
            this.dirtRating = dirtRating;
            this.airRating=airRating;
            this.paradisusRating=paradisusRating;
            this.kitchenRating=kitchenRating;
            this.preferredLength = preferredLength;
            this.currentPosition = 2;
            this.finishedRace = false;
            this.number = 0;

            //thing for making ruhao op
            if(this.name.equals("Ruhao")){
                this.mudRating=Integer.MAX_VALUE;
                this.grassRating=Integer.MAX_VALUE;
                this.dirtRating=Integer.MAX_VALUE;
                this.airRating=Integer.MAX_VALUE;
                this.paradisusRating=Integer.MAX_VALUE;
                this.kitchenRating=Integer.MAX_VALUE;
            }
        }
        public void setLength(double length){
            preferredLength=length;
        }
        
        //getters and setters for attributes

        public void setNumber(int number){
            this.number = number;
        }

        public int getNumber(){
            return this.number;
        }

        public void setRaceFinished(boolean finished){
            finishedRace = finished;
        }

        public boolean raceFinished(){
            return finishedRace;
        }
        public String getName() {
            return name;
        }

        public int getMudRating() {
            return mudRating;
        }

        public int getGrassRating() {
            return grassRating;
        }

        public int getDirtRating() {
            return dirtRating;
        }

        public int getAirRating(){
            return airRating;
        }

        public int getParadisusRating(){
            return paradisusRating;
        }

        public int getKitchenRating(){
            return kitchenRating;
        }
        
        public double getPreferredLength() {
            return preferredLength;
        }

        public void incrementPosition(int inc){
            currentPosition += inc;
        }

        public int getCurrentPosition(){
            return currentPosition;
        }

        public void resetCurrenPosition(){
            currentPosition = 2;
            finishedRace = false;
        }
       
    }