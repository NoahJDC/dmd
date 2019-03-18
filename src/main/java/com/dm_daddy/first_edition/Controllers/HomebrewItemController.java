package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.HomebrewItems;
import com.dm_daddy.first_edition.Model.Items;
import com.dm_daddy.first_edition.Model.RefCode;
import com.dm_daddy.first_edition.Repositories.HomebrewItemRepository;
import com.dm_daddy.first_edition.Repositories.RefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RepositoryRestController
@CrossOrigin
public class HomebrewItemController {

    @Autowired
    private HomebrewItemRepository repo;

    @Autowired
    private RefCodeRepository refRepo;


    //----- Load All Items --------
    //-----------------------------
    @GetMapping("/homebrewItems/all")
    public Page<HomebrewItems> getAllItems(@RequestParam int page, @RequestParam int size){
        Page<HomebrewItems> homebrewItemsList = (Page<HomebrewItems>) repo.getAllHomebrewItems(PageRequest.of(page, size));
        return homebrewItemsList;
    }

    //---- Load Item Type -------
    //---------------------------
    @GetMapping("/homebrewItems/type")
    public List<RefCode> getItemType(){
        List<RefCode> itemTypes = (List<RefCode>) refRepo.findByParentId((long) 9);
        return itemTypes;
    }

    //----- Load Rarity ---------
    //---------------------------
    @GetMapping("/homebrewItems/rarity")
    public List<RefCode> getRarity(){
        List<RefCode> rarity = refRepo.findByParentId((long) 1);
        return rarity;
    }

    //---- Load Attunement -----
    //--------------------------
    @GetMapping("/homebrewItems/attunement")
    public List<RefCode> getAttunment(){
        List<RefCode> attunement = refRepo.findByParentId((long) 19);
        return attunement;
    }

    //---- Load Armor Type ------
    //---------------------------
    @GetMapping("/homebrewItems/armorType")
    public List<RefCode> getArmorType(){
        List<RefCode> armorType = refRepo.findByParentId((long) 22);
        return armorType;
    }

    //----- Load Weapon Type ---
    //--------------------------
    @GetMapping("/homebrewItems/weaponType")
    public List<RefCode> getWeaponType(){
        List<RefCode> weaponType = refRepo.findByParentId((long) 37);
        return weaponType;
    }

    //---- Create an Item -------
    //---------------------------
    @RequestMapping(value="/homebrewItems/create")
    @PostMapping
    public HomebrewItems createItem(@RequestBody HomebrewItems homebrewItem){
        HomebrewItems createdHomebrewItem = repo.save(homebrewItem);
        return createdHomebrewItem;
    }

    //---- Delete an Item -----
    //-------------------------
    @RequestMapping(value="/homebrewItems/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<HomebrewItems> deleteItem(@PathVariable Long id){
        repo.deleteById(id);
        List<HomebrewItems> homebrewItemList = (List<HomebrewItems>) repo.findAll();
        return homebrewItemList;
    }

}