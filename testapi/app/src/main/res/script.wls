#!/usr/bin/env wolframscript
(* ::Package:: *)

SetDirectory["C:\\Users\\cyamo\\Documents\\GitHub\\PeacefulExpedition"];
training=Transpose[Partition[StringTrim[DeleteStopwords[StringSplit[Import["trainingdata.txt"],"\n"]]],2]];
c=Classify[Normal[AssociationThread[training[[1]],training[[2]]]],PerformanceGoal->"Quality"];
Export["output.txt",c[$ScriptCommandLine[[1]]]];
