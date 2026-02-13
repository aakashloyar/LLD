# DRY - Don't Repeat Yourself

-> suppose in 2 classes 
class Men() {
    double BMI(int height, int weight) {}
}
class Women() {
    double BMI(int height, int weight) {}
}

-> instead of this make a single class that has this bmi calculation method
-> so that there is only 1 single source of truth

class BMICalculator() {
    double BMI(int height, int weight) {}
}