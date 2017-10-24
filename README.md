# taskr

[Promotional site](https://parthsareen.github.io/)

## About

_taskr_ is an app that auto manages and schedules activities using machine learning. taskr makes an individual’s life simple by allowing them to effortlessly arrange their schedule. taskr is based on machine learning and learns as the individual tracks their work, extra-curriculars, essentials, leisure, and sports. The app also uses principles of psychology to help an individual learn to their greatest potential by using techniques for studying which were recently published in studies showing memory improvement for tests.
This project was undertaken at [THacks2](https://thacks2.devpost.com/).


## App

The app is written in java and built with gradle. It is implemented in native android and will be found in the `Scheduler` folder.

## Classifier

In the case where two or more tasks have equal urgencies, the classifier may be used to determine the prevailing task. We would want the client to alternate tasks based on category. For example, we would want the client who has just finished an hour of basketball to experience a leisure task instead of an excercise task, given the leisure task and the excercise task have equal urgencies. The classifier is written in the Wolfram Language. The version 0.2 of the classifier will be found in **NN1.nb** and version 0.1 of the classifier will be found in **NN.b**. We transitioned from manually training the classifier to having the classifier infer parameters from training data. Both versions are supervised although unsupervised machine learning was briefly experimented. Both versions are implemented with Markov method, typical of text and audio classification. The training data will be found in `trainingdata.txt`. The command line script will be found in ``script.wls``. The current accuracy of the classifier is 75%.

## Next Steps

Due to the proprietary nature of Wolfram Mathematica, hefty charges were imposed on cloud API deployment in which HTTP requests would be otherwise be able to access the classifier indefinitely. Thus, the classifier was not integrated into the app as originally planned. The complete elimination of the need for proprietary software is required. The optimal solution is to shift the classifier onto the R programming language and its `nnet` (neural net) library.

Though originally proposed, tracking progress and incentive programs were not implemented. The point curve was carefully conceived to discourage overworking for long periods of time and promote diversitization and short intervals of work. In tandem, these aspects may boost user experience and engagement.
