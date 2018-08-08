//
//  FaceView.swift
//  Faceit
//
//  Created by Tim Serio on 6/20/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

@IBDesignable
class FaceView: UIView {
    
    //making all properties configurable with public variables
    //making all properties inspectable
    @IBInspectable
    var scale: CGFloat = 0.9 { didSet { setNeedsDisplay() } }
    
    @IBInspectable
    var eyesOpen: Bool = true { didSet { setNeedsDisplay() } }
    
    // 1.0 = full smile and -1 is full frown
    @IBInspectable
    var mouthCurve: Double = 1.0 { didSet { setNeedsDisplay() } }
    
    @IBInspectable
    var lineWidth: CGFloat = 5.0 { didSet { setNeedsDisplay() } }
    
    @IBInspectable
    var color: UIColor = UIColor.blue { didSet { setNeedsDisplay() } }
    
    func changeScale(byReactingTo pinchRecognizer: UIPinchGestureRecognizer) {
        switch pinchRecognizer.state {
            case .changed, .ended:
                scale *= pinchRecognizer.scale
                pinchRecognizer.scale = 1
            default:
                break
        }
    }
    
    private var headRadius: CGFloat {
        return min(bounds.size.width, bounds.size.height) / 2 * scale
    }
    
    private var headCenter: CGPoint {
        return CGPoint(x: bounds.midX, y: bounds.midY)
    }
    
    private enum Eye {
        case left
        case right
    }
    
    private func pathForEye(_ eye: Eye) -> UIBezierPath {
        func centerOfEye(_ eye: Eye) -> CGPoint {
            let eyeOffset = headRadius / Ratios.headRadiusToEyeOffset
            var eyeCenter = headCenter
            //move y up from center point a distance of eyeOffset
            eyeCenter.y -= eyeOffset
            eyeCenter.x += ((eye == .left) ? -1 : 1) * eyeOffset
            return eyeCenter
        }
        
        let eyeRadius = headRadius / Ratios.headRadiusToEyeRadius
        let eyeCenter = centerOfEye(eye)
        
        let path: UIBezierPath
        if eyesOpen{
            path = UIBezierPath(arcCenter: eyeCenter, radius: eyeRadius, startAngle: 0, endAngle: CGFloat.pi * 2, clockwise: true)
        } else {
            path = UIBezierPath()
            path.move(to: CGPoint(x: eyeCenter.x - eyeRadius, y: eyeCenter.y))
            path.addLine(to: CGPoint(x: eyeCenter.x + eyeRadius, y: eyeCenter.y))
        }
        path.lineWidth = lineWidth
        return path
    }
    
    private func pathForMouth() -> UIBezierPath {
        let mouthWidth = headRadius / Ratios.headRadiusToMouthWidth
        let mouthHeight = headRadius / Ratios.headRadiusToMouthHeight
        let mouthOffset = headRadius / Ratios.headRadiusToMouthOffset
        
        let mouthRect = CGRect(x: headCenter.x - mouthWidth / 2, y: headCenter.y + mouthOffset, width: mouthWidth, height: mouthHeight)
        
        let smileOffset = CGFloat(max(-1,min(mouthCurve, 1))) * mouthRect.height
        
        let start = CGPoint(x: mouthRect.minX, y: mouthRect.midY)
        let end = CGPoint(x: mouthRect.maxX, y: mouthRect.midY)
        
        let controlPoint1 = CGPoint(x: start.x + mouthRect.width / 3, y: start.y + smileOffset)
        let controlPoint2 = CGPoint(x: end.x - mouthRect.width / 3, y: start.y + smileOffset)
        let path = UIBezierPath()
        path.move(to: start)
        path.addCurve(to: end, controlPoint1: controlPoint1, controlPoint2: controlPoint2)
        path.lineWidth = lineWidth
        return path
    }
    
    private func pathForHead() -> UIBezierPath {
        let path = UIBezierPath(arcCenter: headCenter, radius: headRadius, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: false)
        path.lineWidth = lineWidth
        return path
    }

    override func draw(_ rect: CGRect) {
        color.set()
        pathForHead().stroke()
        pathForEye(.left).stroke()
        pathForEye(.right).stroke()
        pathForMouth().stroke()
    }
    
    private struct Ratios {
        static let headRadiusToEyeOffset: CGFloat = 3
        static let headRadiusToEyeRadius: CGFloat = 10
        static let headRadiusToMouthWidth: CGFloat = 1
        static let headRadiusToMouthHeight: CGFloat = 3
        static let headRadiusToMouthOffset: CGFloat = 3
    }

}
