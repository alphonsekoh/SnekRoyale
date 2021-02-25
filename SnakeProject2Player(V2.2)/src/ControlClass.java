class controlClass{

    private int move;

    private int allowInput;
    private int left;
    private int right;
    private int up;
    private int down;

    private int a;
    private int d;
    private int w;
    private int s;

    public controlClass() {
        move = 0;

        allowInput = 0;
        left = 0;
        right = 0;
        up = 0;
        down = 0;

        a = 0;
        d = 0;
        w = 0;
        s = 0;

    }
    public void setMove(int newMove) {

        move = newMove;

    }
    public int getMove() {

        return move;

    }
    public void setAllowInput(int newInput) {

        allowInput = newInput;

    }
    public int getAllowInput() {

        return allowInput;

    }
    public void setKeyTrueFalseValue(String key, int newValue) {
        System.out.println("inside set method set value: ");
        System.out.println(newValue);
        System.out.println("inside set method set target: ");
        System.out.println(key);

        // 0  represents off,1 represent on

        if (key == "w") {
            w = newValue;
        }
        else if (key == "a") {
            a = newValue;
        }
        else if (key == "s") {
            s = newValue;
        }
        else if (key == "d") {
            d = newValue;
        }
        else if (key == "left") {
            left = newValue;
        }
        else if (key == "up") {
            up = newValue;
        }
        else if (key == "down") {
            down = newValue;
        }
        else if (key == "right") {
            right = newValue;
        }
        System.out.println("inside set trueFalseValue before exiting check w a s d");
        System.out.println(w);
        System.out.println(a);
        System.out.println(s);
        System.out.println(d);

    }
    public int getKeyTrueFalseValue(String key) {

        int tempInt = 0;
        switch(key) {
            case "left":
                tempInt = left;
                break;

            case "right":
                tempInt = right;
                break;

            case "up":
                tempInt = up;
                break;

            case "down":
                tempInt = down;
                break;

            case "a":
                tempInt = a;
                break;

            case "w":
                tempInt = w;
                break;

            case "s":
                tempInt = s;
                break;

            case "d":
                tempInt = d;
                break;

        }

        return tempInt;
    }

    public void blockControl() {

        //if(p == 1) {  //if player 1 calls the blockControl

        a = 0;
        d = 0;
        w = 0;
        s = 0;

        //}
        //else if(p == 2) { //if player 2 calls the blockcontrol

        left = 0;
        right = 0;
        up = 0;
        down = 0;

        //}

    }

}