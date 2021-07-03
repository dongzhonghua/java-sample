package xyz.dsvshx.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author dongzhonghua
 * Created on 2021-06-26
 */
public class YXScoreSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Score> scores = new ArrayList<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] s1 = s.split(" ");
            Score score = new Score();
            score.name = s1[0];
            score.chinese = Integer.parseInt(s1[1]);
            score.math = Integer.parseInt(s1[2]);
            score.english = Integer.parseInt(s1[3]);
            score.total = score.chinese + score.math + score.english;
            scores.add(score);
        }
        //  score up
        Comparator<Score> scoreComparator = (o1, o2) -> {
            if (o1.total != o2.total) {
                return o1.total - o2.total;
            } else if (o1.chinese != o2.chinese) {
                return o1.chinese - o2.chinese;
            } else if (o1.math != o2.math) {
                return o1.math - o2.math;
            } else if (o1.english != o2.english) {
                return o1.english - o2.english;
            } else {
                return o2.name.compareTo(o1.name);
            }
        };
        scores.sort(scoreComparator);
        int rank = 1;
        for (int i = scores.size() - 1; i >= 0; i--) {
            Score score = scores.get(i);
            //int rank = scores.size() - i;
            if (rank > 1 && score.total == scores.get(i + 1).total
                    && score.chinese == scores.get(i + 1).chinese
                    && score.math == scores.get(i + 1).math
                    && score.english == scores.get(i + 1).english) {
            }else{
                rank = scores.size() - i;
            }
            System.out.printf("rank:%d name:%s total:%d chinese:%d math:%d english:%d\n", rank, score.name,
                    score.total,
                    score.chinese, score.math, score.english);

        }
    }
}

class Score {
    String name;
    int total;
    int chinese;
    int math;
    int english;
}

