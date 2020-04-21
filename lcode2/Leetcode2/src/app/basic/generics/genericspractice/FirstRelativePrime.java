package app.basic.generics.genericspractice;

import java.util.Arrays;
import java.util.List;

import app.math.MyMath;

class UnaryPredicateImpl implements UnaryPredicate<Integer> {
    private List<Integer> target;

    public UnaryPredicateImpl(List<Integer> target) {
        this.target = target;
    }

    @Override
    public boolean test(Integer model) {
        for (int t : target) {
            if (MyMath.gcd(t, model) != 1) {
                return false;
            }
        }
        return true;
    }

}

public class FirstRelativePrime {
    public static <T> int findFirstPrime(List<T> srcList, int begin, 
                                        int end, UnaryPredicate<T> target) {
        int i = 0;
        for (T model : srcList) {
            if (i >= begin && i < end) {
                if (target.test(model)) {
                    return i;
                }
            } else if (i >= end) {
                break;
            }
            i++;
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> srcList = Arrays.asList(3, 4, 6, 8, 11, 15, 28, 32);
        List<Integer> target = Arrays.asList(7, 18, 19, 25);

        int idx = findFirstPrime(srcList, 0, srcList.size(), new UnaryPredicateImpl(target));
        if (idx != -1) {
            System.out.print(srcList.get(idx));
        } else {
            System.out.print("No element");
        }
        System.out.println(" is the relative prime in " + target);
    }
}