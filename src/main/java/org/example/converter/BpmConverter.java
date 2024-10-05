package org.example.converter;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.example.graph.Graph;
import org.example.utils.GlobalConstants;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class BpmConverter {
    public Graph convertBpmToGraph(String startNode) {
        var graph = new Graph();
        var bpmFile = Path.of(GlobalConstants.BPM_FILE_LOCATION).toFile();
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(bpmFile);
        var start = modelInstance.getModelElementById(startNode);
        var startFlowNode = convertToFlowNode(start);
        Set<String> visited = new HashSet<>();
        buildGraph(graph, startFlowNode, visited);
        return graph;
    }

    private void buildGraph(Graph graph, FlowNode currentNode, Set<String> visited) {
        String currentId = currentNode.getAttributeValue(GlobalConstants.NODE_ID_KEY);
        if (visited.contains(currentId)) {
            return;
        }
        visited.add(currentId);
        graph.addVertex(currentId);

        var succeedingNodes = currentNode.getSucceedingNodes().list();
        for (FlowNode nextNode : succeedingNodes) {
            String nextId = nextNode.getAttributeValue(GlobalConstants.NODE_ID_KEY);
            graph.addEdge(currentId, nextId);
            buildGraph(graph, nextNode, visited);
        }
    }

    public FlowNode convertToFlowNode(ModelElementInstance elementInstance) {
        if (elementInstance instanceof FlowNode flowNode) {
            return flowNode;
        } else {
            throw new IllegalArgumentException("Element instance is not a FlowNode: " + elementInstance.getElementType());
        }
    }
}
