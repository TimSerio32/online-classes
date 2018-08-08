//
//  PokemonDetailViewController.swift
//  Pokedex
//
//  Created by Tim Serio on 9/13/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit


class PokemonDetailViewController: UIViewController {

    var pokemon: Pokemon!
    
    @IBOutlet weak var nameLbl: UILabel!
    @IBOutlet weak var mainImg: UIImageView!
    @IBOutlet weak var descriptionLbl: UILabel!
    @IBOutlet weak var typeLbl: UILabel!
    @IBOutlet weak var defenseLbl: UILabel!
    @IBOutlet weak var heightLbl: UILabel!
    @IBOutlet weak var pokedexLbl: UILabel!
    @IBOutlet weak var weightLbl: UILabel!
    @IBOutlet weak var attackLbl: UILabel!
    @IBOutlet weak var currentEvoImg: UIImageView!
    @IBOutlet weak var nextEvoImg: UIImageView!
    @IBOutlet weak var evoLbl: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()

        nameLbl.text = pokemon.name.capitalized
        let image = UIImage(named: "\(pokemon.pokedexId)")
        mainImg.image = image
        currentEvoImg.image = image
        pokedexLbl.text = "\(pokemon.pokedexId)"
        pokemon.downloadPokemonDetail {
            self.updateUI()
        }
    }
    
    func updateUI() {
       attackLbl.text = pokemon.attack
       defenseLbl.text = pokemon.defense
       heightLbl.text = pokemon.height
       weightLbl.text = pokemon.weight
       typeLbl.text = pokemon.type
       descriptionLbl.text = pokemon.description
        if pokemon.nextEvoId == "" {
            evoLbl.text = "No Evolutions"
            nextEvoImg.isHidden = true
        } else {
            nextEvoImg.isHidden = false
            nextEvoImg.image = UIImage(named: pokemon.nextEvoId)
            let str = "Next Evolution: \(pokemon.nextEvoName) - LVL \(pokemon.nextEvoLevel)"
            evoLbl.text = str
        }
    }
    
    @IBAction func backBtnPressed(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    

}
