//
//  CaclulatorModel.swift
//  Calculator
//
//  Created by Tim Serio on 6/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
class CalculatorModel{
    
    /*func addUnaryOperation(named symbol: String, _ operation: @escaping (Double) -> Double) {
        operations[symbol] = Operation.unaryOperation(.postfix("✅"), operation)
    }*/
    
    private var accumulator = 0.0
    
    private var descriptionHelper = [Any]()
    
    
    func setOperand(_ operand: Double) {
        if resultIsPending == nil{
            clear()
        }
        accumulator = operand
        descriptionHelper.append(operand)
    }
    
    
    private enum Operation {
        case constant(Double)
        case unaryOperation(PrintSymbol, (Double) -> Double)
        case binaryOperation((Double,Double) -> Double)
        case equals
        
        enum PrintSymbol {
            case prefix(String)
            case postfix(String)
        }
    }
    
    private var operations: Dictionary<String,Operation> = [
        "π" : Operation.constant(Double.pi),
        "e" : Operation.constant(M_E),
        "√" : Operation.unaryOperation(.postfix("√"), sqrt),
        "cos" : Operation.unaryOperation(.postfix("cos"), cos),
        "sin" : Operation.unaryOperation(.postfix("sin"), sin),
        "log" : Operation.unaryOperation(.postfix("log"), log),
        "%" : Operation.unaryOperation(.prefix("%")) { $0 / 100 },
        "±" : Operation.unaryOperation(.prefix(" * -1")) { -$0 },
        "*" : Operation.binaryOperation({ $0 * $1 }),
        "+" : Operation.binaryOperation({ $0 + $1 }),
        "-" : Operation.binaryOperation({ $0 - $1 }),
        "/" : Operation.binaryOperation({ $0 / $1 }),
        "=" : Operation.equals
    ]
    
    
    
    func performOperation(_ symbol: String) {
        if let operation = operations[symbol] {
            switch(operation) {
                case .constant(let value):
                    if resultIsPending == nil {
                        clear()
                    }
                    accumulator = value
                
                case .unaryOperation(_, let functn):
                    accumulator = functn(accumulator)
                
                case .binaryOperation(let functn):
                    executePendingBinaryOperation()
                    resultIsPending = PendingBinaryOperationInfo(binaryFunc: functn, firstOperand: accumulator)
                
                case .equals:
                    executePendingBinaryOperation()
                
            
            }
            descriptionHelper.append(symbol)
        }
    }
    
    private func executePendingBinaryOperation(){
        if let resultIsPending = resultIsPending {
            accumulator = resultIsPending.binaryFunc(resultIsPending.firstOperand, accumulator)
            self.resultIsPending = nil
        }
    }
    
    
        
    var partialResult: Bool{
        return (resultIsPending != nil)
    }
        
    private var resultIsPending: PendingBinaryOperationInfo?
    
    private struct PendingBinaryOperationInfo {
        var binaryFunc: (Double, Double) -> Double
        var firstOperand: Double
    }
        
    var numFormatter: NumberFormatter?
        
    var description: String {
        var resultString = ""
        for property in descriptionHelper{
            if let operand = property as? Double{
                let stringToAppend = String(operand)
                resultString = resultString + stringToAppend
            }else if let symbol = property as? String{
                if let operation = operations[symbol]{
                    switch(operation){
                    case .constant, .binaryOperation:
                        resultString = resultString + symbol
                        
                    case .unaryOperation(let printSymbol, _):
                        switch(printSymbol){
                        case .prefix(let symbol):
                            resultString = "(" + resultString + ")" + symbol
                            
                        case .postfix(let symbol):
                            resultString = symbol + "(" + resultString + ")"
                        }
                        
                    default:
                        break
                    }
                }
            }
        }
        return resultString
    }
    
    func clear() {
        accumulator = 0.0
        resultIsPending = nil
        descriptionHelper = []
    }
    

    var result: Double {
        get{
            return accumulator
        }
    }
}
