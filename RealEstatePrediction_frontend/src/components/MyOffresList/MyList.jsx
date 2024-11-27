import './Mylist.scss'
import CardMyOffre from "../cardMyOffre/cardMyOffre"
import {listData} from "../../lib/dummydata"

function MyList(){
  return (
    <div className='list'>
      {listData.map(item=>(
        <CardMyOffre key={item.id} item={item}/>
      ))}
    </div>
  ) 
}

export default MyList