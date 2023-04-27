import React from "react"

class ListOfVideos extends React.Component{
    constructor(props){
        super(props)
        this.state = {data: []}
    }
    
    async componentDidMount(){
        let json = await (await fetch("http://localhost:8080/api/videos")).json();
        let dataArray = Array.from(json);
        this.setState({data: dataArray});
    }

    render(){
       console.log("Data array length:", this.state.data.length);
       return  (
        <div>
        <ul>
            <li>Hello</li>
        </ul>
        <ul>
            {this.state.data.map(item =>
                <li>
                    {item.name}
                </li>
                )}
        </ul>

        </div>
       )
    }
}

export default ListOfVideos
