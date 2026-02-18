package Problems.Concurrency;

import javax.sound.midi.ShortMessage;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CiggerateSmoker {
    public static void main(String[] args) {

    }
}
class CS {
    static Random random = new Random();
    Semaphore agent=new Semaphore(1);
    Semaphore tobacoo=new Semaphore(0);
    Semaphore paper=new Semaphore(0);
    Semaphore match =new Semaphore(0);
    CS() {

    }
    void Agent() {
        try{
            while(true) {
                agent.acquire();
                int n= random.nextInt(3);
                if(n==0) {
                    System.out.println("Agent puts Paper and Match");
                    tobacoo.release();
                } else if(n==1) {
                    System.out.println("Agent puts Match and Tobacco");
                    paper.release();
                } else {
                    System.out.println("Agent puts Tobacoo and Paper");
                    match.release();
                }
                agent.release();
            }
        } catch(Exception e){}

    }
    void TobacooSmoker() {
        while(true) {
            try {
                tobacoo.acquire();
                System.out.println("Tobacco Smoker is making cigarette");
                Thread.sleep(1000);
                System.out.println("Tobacco Smoker is smoking");
                Thread.sleep(1000);
                tobacoo.release();
            } catch(Exception e){}
        }
    }
    void PaperSmoker() {
        while(true) {
            try {
                paper.acquire();
                System.out.println("Paper Smoker is making cigarette");
                Thread.sleep(1000);
                System.out.println("Paper Smoker is smoking");
                Thread.sleep(1000);
                paper.release();
            } catch(Exception e){}
        }
    }
    void MatchSmoker() {
        while(true) {
            try {
                match.acquire();
                System.out.println("Match Smoker is making cigarette");
                Thread.sleep(1000);
                System.out.println("Match Smoker is smoking");
                Thread.sleep(1000);
                match.release();
            } catch(Exception e){}
        }
    }
}

//CiggerateSmoker problem
//MatchSmoker
//PaperSmoker
//TobacooSmoker
//Agent
//Agent randomly puts 2 of three things and which 1 have the third will execute
