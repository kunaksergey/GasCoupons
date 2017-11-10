const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {priceList: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/v1/priceList'}).done(response => {
            this.setState({priceList: response.entity._embedded.priceList});
        });
    }

    render() {
        return (
            <PriceList employees={this.state.priceList}/>
        )
    }
}
class PriceList extends React.Component{
    render() {
        var pricelist = this.props.pricelist.map(pricelist =>
            <Price key={price._links.self.href} price={price}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>price</th>
                </tr>
                {employees}
                </tbody>
            </table>
        )
    }
}

class Price extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.price.id}</td>
                <td>{this.props.price.name}</td>
                <td>{this.props.price.price}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)