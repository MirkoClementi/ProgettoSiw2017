
var app = angular.module('myApp', ['angular.filter'])


app.controller('myCtrl', function($scope,$http) {
    /*$scope.paintings = [
{title:'title1',src:'img/397x300/01.jpg',desc:'Image01',technique:'acquerello',size:'50x50',age:1800,artist:'Picasso'},
{title:'title2',src:'img/397x300/02.jpg',desc:'Image02',technique:'aereografia',size:'70x50',age:1700,artist:'Paolone'},
{title:'title3',src:'img/397x300/03.jpg',desc:'Image03',technique:'carboncino',size:'70x50',age:1750,artist:'Picasso'},
{title:'title4',src:'img/397x300/04.jpg',desc:'Image04',technique:'sfumato',size:'70x50',age:1800,artist:'Michelangelo'},
{title:'title5',src:'img/397x300/03.jpg',desc:'Image05',technique:'carboncino',size:'100x80',age:1700,artist:'Luchetto'},
{title:'title6',src:'img/397x300/04.jpg',desc:'Image06',technique:'pennello',size:'100x80',age:2000,artist:'Picasso'}
    ];*/
 
     $http.get('json/painting.json')
       .then(function(res){
          $scope.paintings = res.data;                
        });
     
    
    $scope.artists = [
{name:'artistName1',surname:'artistSurname1',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/01.jpg'},
{name:'artistName2',surname:'artistSurname2',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/02.jpg'},
{name:'artistName3',surname:'artistSurname3',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/03.jpg'},
{name:'artistName4',surname:'artistSurname4',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/03.jpg'},
{name:'artistName5',surname:'artistSurname5',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/02.jpg'},
{name:'artistName6',surname:'artistSurname6',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/01.jpg'},
{name:'artistName7',surname:'artistSurname7',nationality:'world',birthday:new Date("25 Mar 2015"),deathday:'none',src:'img/397x400/01.jpg'},

    ];
    
     $scope.paintingsArrayTech = [{technique:"acquerello" ,on:false},{technique:"aereografia" ,on:false},{technique:"carboncino" ,on:false},{technique:"sfumato" ,on:false},{technique:"carboncino" ,on:false},{technique:"pennello" ,on:false}];
    
     $scope.paintingsArraySize = [{dimension:"50x50" ,on:false},{dimension:"70x50" ,on:false},{dimension:"70x50" ,on:false},{dimension:"70x50" ,on:false},{dimension:"100x80" ,on:false},{dimension:"100x80" ,on:false}];
    
     $scope.paintingsArrayAge = [{year:1800 ,on:false},{year:1700 ,on:false},{year:1750 ,on:false},{year:1800 ,on:false},{year:1700,on:false},{year:2000,on:false}];
    
    //Scripting starts
    $scope.stringFilter = function(pic){
    if($scope.NameTitleFilter==undefined) {
             return true;
        }
        else {
            if((pic.title.toLowerCase().indexOf($scope.NameTitleFilter.toLowerCase())!=-1)||            (pic.artist.toLowerCase().indexOf($scope.NameTitleFilter.toLowerCase())!=-1))
            {
                return true;
            }
        }
        return false;
    };
    
    //checkChangeFunction()
    
    $scope.showAll = true;
    $scope.checkChange = function() {
        for(t in $scope.paintingsArrayTech){
            if($scope.paintingsArrayTech[t].on){
                $scope.showAll = false;
                return;
            }
        }
         for(t in $scope.paintingsArraySize){
            if($scope.paintingsArraySize[t].on){
                $scope.showAll = false;
                return;
            }
        }
         for(t in $scope.paintingsArrayAge){
            if($scope.paintingsArrayAge[t].on){
                $scope.showAll = false;
                return;
            }
        }
        $scope.showAll = true;
    };
    
    //Filters JS
    
   $scope.myFilterTech = function(a) {
       if($scope.showAll) { return true; }
       
       var sel = false;
       
        for(tech in $scope.paintingsArrayTech){
            var t = $scope.paintingsArrayTech[tech];
            console.log(t);
            if(t.on){
                if(a.technique.indexOf(t.technique) == -1){
                    return false;
                }else{
                    sel = true;
                }
            }           
        }
       return sel;
    };
    
     $scope.myFilterSize= function(a) {
       if($scope.showAll) { return true; }
       
       var sel = false;
       
        for(tech in $scope.paintingsArraySize){
            var t = $scope.paintingsArraySize[tech];
            console.log(t);
            if(t.on){
                if(a.size.indexOf(t.size) == -1){
                    return false;
                }else{
                    sel = true;
                }
            }           
        }
       return sel;
    };
    
     $scope.myFilterAge = function(a) {
       if($scope.showAll) { return true; }
       
       var sel = false;
       
        for(tech in $scope.paintingsArrayAge){
            var t = $scope.paintingsArrayAge[tech];
            console.log(t);
            if(t.on){
                if(a.age.indexOf(t.age) == -1){
                    return false;
                }else{
                    sel = true;
                }
            }           
        }
       return sel;
    };
});







/* JSONLOADER

$http.get('json/painting.json')
       .then(function(res){
          $scope.paintings = res.data;                
        });
        
//JSON LOADER needed to separate each row with ',' and includes " []" 


 $scope.paintingsArraySize = {
            angular.forEach($scope.paintings,function(value)){
               $scope.paintingsArraySize.size.push(value.size);
                $scope.paintingsArraySize.on.push(false):
            }
        };
*/
